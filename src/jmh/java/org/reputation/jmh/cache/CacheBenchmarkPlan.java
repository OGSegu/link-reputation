package org.reputation.jmh.cache;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.reputation.persistence.LinkReputationCache;
import org.reputation.persistence.LinkReputationCacheWithBloomFilter;
import org.reputation.persistence.сonfig.BloomFilterConfig;
import org.reputation.persistence.сonfig.LinkReputationCacheConfig;

@State(Scope.Benchmark)
public class CacheBenchmarkPlan {

    protected static final int URL_MIN_LENGTH = 10;
    protected static final int URL_MAX_LENGTH = 50;

    private static final double PREDICTION_MIN_VALUE = -1.0;
    private static final double PREDICTION_MAX_VALUE = 1.0;

    private static final int REPUTATION_ENTRIES_AMOUNT = 500_000;

    public List<Pair<String, Double>> urlToPrediction = new ArrayList<>();

    public LinkReputationCache cache;

    @Param({"true", "false"})
    public boolean useBloomFilter;

    public int cacheMissStep = 2;

    @Setup(Level.Trial)
    public void setUp() throws IOException {
        LinkReputationCacheConfig cacheConfig = getCacheConfig();
        Files.deleteIfExists(Path.of(cacheConfig.getFileName()));

        for (int i = 0; i < REPUTATION_ENTRIES_AMOUNT; i++) {
            if (i % cacheMissStep == 0) {
                continue;
            }
            String url = RandomStringUtils.randomAlphabetic(URL_MIN_LENGTH, URL_MAX_LENGTH);
            double prediction = ThreadLocalRandom.current()
                    .nextDouble(PREDICTION_MIN_VALUE, PREDICTION_MAX_VALUE + 1);
            urlToPrediction.add(Pair.of(url, prediction));
        }
        this.cache = useBloomFilter
                ? new LinkReputationCacheWithBloomFilter(cacheConfig, getBloomFilterConfig())
                : new LinkReputationCache(cacheConfig);
    }



    private static LinkReputationCacheConfig getCacheConfig() {
        return new LinkReputationCacheConfig() {
            @Override
            public int getCapacity() {
                return REPUTATION_ENTRIES_AMOUNT;
            }

            @Override
            public String getFileName() {
                return "temp";
            }

            @Override
            public long getFileSize() {
                return 104_857_600L; // 100mb
            }

            @Override
            public long getExpirationTime() {
                return 0;
            }
        };
    }

    private static BloomFilterConfig getBloomFilterConfig() {
        return new BloomFilterConfig() {

            @Override
            public double getFalsePositiveRate() {
                return 0.03;
            }

            @Override
            public int getWorkersAmountToInit() {
                return 0;
            }
        };
    }


}
