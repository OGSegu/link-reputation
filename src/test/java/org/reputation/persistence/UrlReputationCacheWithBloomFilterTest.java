package org.reputation.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reputation.persistence.сonfig.BloomFilterConfig;
import org.reputation.persistence.сonfig.LinkReputationCacheConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class UrlReputationCacheWithBloomFilterTest {

    public static final String fileName = "test";
    private UrlReputationCacheWithBloomFilter urlReputationCache;

    @BeforeEach
    void setUp() throws IOException {
        urlReputationCache = new UrlReputationCacheWithBloomFilter(new LinkReputationCacheConfig() {
            @Override
            public int getCapacity() {
                return 100;
            }

            @Override
            public String getFileName() {
                return fileName;
            }

            @Override
            public long getFileSize() {
                return 2097152;
            }

            @Override
            public long getExpirationTime() {
                return 1;
            }
        }, new BloomFilterConfig() {
            @Override
            public double getFalsePositiveRate() {
                return 0.01;
            }

            @Override
            public int getWorkersAmountToInit() {
                return 1;
            }
        });
    }

    @AfterEach
    public void afterEach() throws IOException {
        urlReputationCache.close();
        Files.deleteIfExists(Path.of(fileName));
    }

    @Test
    @DisplayName("Should put the URL and its reputation into the cache successfully")
    void putUrlAndReputationIntoCache() {
        String url = "https://example.com";
        double reputation = 0.8;

        boolean result = urlReputationCache.put(url, reputation);

        assertTrue(result);
        assertEquals(reputation, urlReputationCache.get(url));
    }

    @Test
    @DisplayName("Should update the reputation of an existing URL in the cache")
    void putUrlWhenUrlExistsThenUpdateReputation() {
        String url = "https://example.com";
        double initialReputation = 0.5;
        double updatedReputation = 0.8;

        boolean result = urlReputationCache.put(url, initialReputation);
        assertTrue(result);

        Double retrievedReputation = urlReputationCache.get(url);
        assertEquals(initialReputation, retrievedReputation);

        result = urlReputationCache.put(url, updatedReputation);
        assertFalse(result);

        retrievedReputation = urlReputationCache.get(url);
        assertEquals(updatedReputation, retrievedReputation);
    }

    @Test
    @DisplayName("Testing basics of bloom filter visitor")
    void testBloomFilterVisitor() {
        urlReputationCache.sharedMemoryMap.put("test.com", 0.5);
        UrlReputationCacheWithBloomFilter.BloomFilterInitializationVisitor visitor
                = new UrlReputationCacheWithBloomFilter.BloomFilterInitializationVisitor(urlReputationCache.bloomFilter);
        urlReputationCache.sharedMemoryMap.iterate(visitor);
        assertTrue(urlReputationCache.bloomFilter.mightContain("test.com"));
    }
}