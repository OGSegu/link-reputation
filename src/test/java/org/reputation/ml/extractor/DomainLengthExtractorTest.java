package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainLengthExtractorTest {

    private DomainLengthExtractor domainLengthExtractor;

    @BeforeEach
    void setUp() {
        domainLengthExtractor = new DomainLengthExtractor();
    }

    @Test
    @DisplayName("Should return -1 when a null URL is provided")
    void extractDomainLengthWhenNullUrlIsProvided() {
        String url = null;
        double result = domainLengthExtractor.extract(url);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Should return -1 when an invalid URL is provided")
    void extractDomainLengthWhenInvalidUrlIsProvided() {
        String invalidUrl = "invali%$1dUrl";
        double expected = -1;

        double actual = domainLengthExtractor.extract(invalidUrl);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the domain length when a valid URL is provided")
    void extractDomainLengthWhenValidUrlIsProvided() {
        String url = "https://www.example.com";
        double expectedDomainLength = 15.0;

        double actualDomainLength = domainLengthExtractor.extract(url);

        assertEquals(expectedDomainLength, actualDomainLength);
    }

}