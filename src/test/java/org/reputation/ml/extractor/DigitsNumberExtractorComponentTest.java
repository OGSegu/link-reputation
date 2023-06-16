package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitsNumberExtractorComponentTest {

    private DigitsNumberExtractorComponent digitsNumberExtractorComponent;

    @BeforeEach
    void setUp() {
        digitsNumberExtractorComponent = new DigitsNumberExtractorComponent();
    }

    @Test
    @DisplayName("Should return zero when there are no digits in the given URL")
    void extractDigitsNumberFromUrlWithNoDigits() {
        String url = "https://example.com";
        double expected = 0.0;
        double actual = digitsNumberExtractorComponent.extract(url);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the correct count of digits in the given URL")
    void extractDigitsNumberFromUrl() {
        String url = "https://example.com/12345";
        double expectedCount = 5.0;

        double actualCount = digitsNumberExtractorComponent.extract(url);

        assertEquals(expectedCount, actualCount);
    }

}