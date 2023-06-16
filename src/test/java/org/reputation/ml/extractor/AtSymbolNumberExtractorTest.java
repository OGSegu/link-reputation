package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtSymbolNumberExtractorTest {

    private FeatureExtractor atSymbolNumberExtractor;

    @BeforeEach
    void setUp() {
        atSymbolNumberExtractor = new AtSymbolNumberExtractor();
    }

    @Test
    @DisplayName("Should return 0 when there are no '@' symbols in the given URL")
    void extractAtSymbolCountWhenNoAtSymbolsInUrl() {
        String url = "https://www.example.com";
        double expectedCount = 0.0;

        double actualCount = atSymbolNumberExtractor.extract(url);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Should return the count of '@' symbols in the given URL")
    void extractAtSymbolCountInUrl() {
        String url = "https://www.example.com/@user/profile";
        double expectedCount = 1.0;
        double actualCount = atSymbolNumberExtractor.extract(url);
        assertEquals(expectedCount, actualCount);
    }

}