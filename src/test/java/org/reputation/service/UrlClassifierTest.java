package org.reputation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reputation.ml.classifier.MLClassifierComponent;
import org.reputation.ml.exception.ClassificationFailedException;
import org.reputation.persistence.UrlReputationCache;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class UrlClassifierTest {

    private UrlClassifier classifierService;
    private MLClassifierComponent mlClassifier;
    private UrlReputationCache cache;

    @BeforeEach
    void setUp() {
        mlClassifier = mock(MLClassifierComponent.class);
        cache = mock(UrlReputationCache.class);
        classifierService = new UrlClassifier(mlClassifier, cache);
    }

    @Test
    @DisplayName("Should return the classification result from cache when the URL is already in cache")
    void classifyWhenUrlIsInCache() throws ClassificationFailedException {
        String url = "https://www.example.com";
        double expectedClassificationResult = 0.8;
        when(cache.get(url)).thenReturn(expectedClassificationResult);

        double actualClassificationResult = classifierService.classify(url);

        assertEquals(expectedClassificationResult, actualClassificationResult);
        verify(mlClassifier, never()).classify(url);
        verify(cache, times(1)).get(url);
        verify(cache, never()).put(url, expectedClassificationResult);
    }

    @Test
    @DisplayName("Should return the classification result from MLClassifier when the URL is not in cache")
    void classifyWhenUrlIsNotInCache() throws ClassificationFailedException {
        String url = "https://www.example.com";
        double expectedClassificationResult = 0.8;

        when(cache.get(url)).thenReturn(null);
        when(mlClassifier.classify(url)).thenReturn(expectedClassificationResult);

        try {
            double actualClassificationResult = classifierService.classify(url);
            assertEquals(expectedClassificationResult, actualClassificationResult);
            verify(cache, times(1)).put(url, expectedClassificationResult);
        } catch (ClassificationFailedException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should store the classification result in cache when the URL is not in cache")
    void classifyStoresResultInCacheWhenUrlIsNotInCache() throws ClassificationFailedException {
        String url = "https://www.example.com";
        double expectedClassificationResult = 0.8;

        when(cache.get(url)).thenReturn(null);
        when(mlClassifier.classify(url)).thenReturn(expectedClassificationResult);

        try {
            double actualClassificationResult = classifierService.classify(url);
            assertEquals(expectedClassificationResult, actualClassificationResult);
            verify(cache, times(1)).put(url, expectedClassificationResult);
        } catch (ClassificationFailedException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

}