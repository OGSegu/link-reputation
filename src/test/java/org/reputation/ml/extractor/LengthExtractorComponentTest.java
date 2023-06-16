package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthExtractorComponentTest {

    private LengthExtractorComponent lengthExtractorComponent;

    @BeforeEach
    void setUp() {
        lengthExtractorComponent = new LengthExtractorComponent();
    }

    @Test
    @DisplayName("Should return zero when the given URL is empty")
    void extractReturnsZeroWhenUrlIsEmpty() {
        String emptyUrl = "";
        double expectedLength = 0.0;

        double actualLength = lengthExtractorComponent.extract(emptyUrl);

        assertEquals(expectedLength, actualLength);
    }

    @Test
    @DisplayName("Should return the length of the given URL")
    void extractReturnsUrlLength() {
        String url = "https://www.example.com";
        double expectedLength = url.length();

        double actualLength = lengthExtractorComponent.extract(url);

        assertEquals(expectedLength, actualLength);
    }

}