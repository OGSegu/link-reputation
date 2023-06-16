package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HyphenCountExtractorComponentTest {

    private HyphenCountExtractorComponent hyphenCountExtractorComponent;

    @BeforeEach
    void setUp() {
        hyphenCountExtractorComponent = new HyphenCountExtractorComponent();
    }

    @Test
    @DisplayName("Should return the correct hyphen count for a given URL")
    void extractHyphenCountForGivenUrl() {
        String url = "www-example-com";
        double expectedHyphenCount = 2.0;

        double actualHyphenCount = hyphenCountExtractorComponent.extract(url);

        assertEquals(expectedHyphenCount, actualHyphenCount);
    }

    @Test
    @DisplayName("Should return zero when there are no hyphens in the URL")
    void extractHyphenCountWhenNoHyphensInUrl() {
        String url = "www.example.com";
        double expectedHyphenCount = 0.0;

        double actualHyphenCount = hyphenCountExtractorComponent.extract(url);

        assertEquals(expectedHyphenCount, actualHyphenCount);
    }

}