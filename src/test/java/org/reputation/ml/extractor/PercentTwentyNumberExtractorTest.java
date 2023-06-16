package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentTwentyNumberExtractorTest {

    private PercentTwentyNumberExtractor percentTwentyNumberExtractor;

    @BeforeEach
    void setUp() {
        percentTwentyNumberExtractor = new PercentTwentyNumberExtractor();
    }

    @Test
    @DisplayName("Should return zero when there are no '%20' occurrences in the given URL")
    void extractNoPercentTwentyOccurrencesInUrl() {
        String url = "https://www.example.com";
        double expected = 0.0;
        double actual = percentTwentyNumberExtractor.extract(url);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the count of '%20' occurrences in the given URL")
    void extractPercentTwentyOccurrencesInUrl() {
        String url = "https://www.example.com/test%20url%20with%20spaces";
        double expectedCount = 3.0;

        double actualCount = percentTwentyNumberExtractor.extract(url);

        assertEquals(expectedCount, actualCount);
    }

}