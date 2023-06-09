package org.reputation.persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import org.reputation.persistence.сonfig.BloomFilterConfig;
import org.reputation.persistence.сonfig.LinkReputationCacheConfig;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import one.nio.mem.OffheapMap;

public class LinkReputationCacheWithBloomFilter extends LinkReputationCache {

    private static final double EXPECTED_INSERTIONS_DIVIDER = 1.75;

    private final BloomFilterConfig bloomFilterConfig;
    private final BloomFilter<String> bloomFilter;

    public LinkReputationCacheWithBloomFilter(LinkReputationCacheConfig cacheConfig,
                                              BloomFilterConfig bloomFilterConfig) throws IOException {
        super(cacheConfig);
        this.bloomFilterConfig = bloomFilterConfig;
        this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8),
                (long) (sharedMemoryMap.getCapacity() / EXPECTED_INSERTIONS_DIVIDER), bloomFilterConfig.getFalsePositiveRate());

    }

    public void init() {
        int workersAmountToInit = bloomFilterConfig.getWorkersAmountToInit();
        if (workersAmountToInit < 2) {
            sharedMemoryMap.iterate(new BloomFilterInitializationVisitor(bloomFilter));
        } else {
            sharedMemoryMap.iterate(new BloomFilterInitializationVisitor(bloomFilter), workersAmountToInit);
        }
        System.out.println("Bloom filter initialized");
    }

    @Override
    public boolean put(String url, double result) {
        boolean isAdded = super.put(url, result);
        if (isAdded) {
            bloomFilter.put(url);
        }
        return isAdded;
    }

    @Nullable
    @Override
    public Double get(String url) {
        if (!bloomFilter.mightContain(url)) {
            return null;
        }
        return super.get(url);
    }

    private static class BloomFilterInitializationVisitor implements OffheapMap.Visitor<String, Double> {

        private final BloomFilter<String> bloomFilter;

        public BloomFilterInitializationVisitor(BloomFilter<String> bloomFilter) {
            this.bloomFilter = bloomFilter;
        }

        @Override
        public void visit(OffheapMap.Record<String, Double> entry) {
            boolean bitsChanged = bloomFilter.put(entry.key());
            if (!bitsChanged) {
                System.out.println("Collision detected");
                // collision detected
                // tune BloomFilter FP rate
            }
        }
    }


}
