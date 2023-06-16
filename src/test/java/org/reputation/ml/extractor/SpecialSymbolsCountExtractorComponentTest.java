package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialSymbolsCountExtractorComponentTest {

    private SpecialSymbolsCountExtractorComponent specialSymbolsCountExtractorComponent;

    @BeforeEach
    void setUp() {
        specialSymbolsCountExtractorComponent = new SpecialSymbolsCountExtractorComponent();
    }

    @Test
    @DisplayName("Should handle empty URL input")
    void extractWhenUrlIsEmpty() {
        String url = "";
        double expectedCount = 0.0;

        double actualCount = specialSymbolsCountExtractorComponent.extract(url);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Should return zero when the URL does not contain any special symbols")
    void extractWhenUrlHasNoSpecialSymbols() {
        String url = "https://www.example.com";
        double expectedCount = 2.0;

        double actualCount = specialSymbolsCountExtractorComponent.extract(url);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Should return the count of special symbols in the given URL")
    void extractSpecialSymbolsCountInUrl() {
        String url = "https://www.example.com?param1=value1&param2=value2";
        double expectedCount = 5.0;

        double actualCount = specialSymbolsCountExtractorComponent.extract(url);

        assertEquals(expectedCount, actualCount);
    }

}