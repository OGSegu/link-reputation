package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainDigitsNumberExtractorTest {

    private DomainDigitsNumberExtractor domainDigitsNumberExtractor;

    @BeforeEach
    void setUp() {
        domainDigitsNumberExtractor = new DomainDigitsNumberExtractor();
    }

    @Test
    @DisplayName("Should return -1 when an invalid URL is provided")
    void extractWhenInvalidUrlIsProvided() {
        String invalidUrl = "invali%;dUrl";
        double result = domainDigitsNumberExtractor.extract(invalidUrl);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Should return the correct number of digits when the domain contains consecutive digits")
    void extractWhenDomainContainsConsecutiveDigits() {
        String url = "https://www.example1234.com";
        double expected = 4.0;
        double actual = domainDigitsNumberExtractor.extract(url);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the correct number of digits when the domain contains non-consecutive digits")
    void extractWhenDomainContainsNonConsecutiveDigits() {
        String url = "https://www.example123.com";
        double expected = 3.0;

        double actual = domainDigitsNumberExtractor.extract(url);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return 0 when the domain contains no digits")
    void extractWhenDomainContainsNoDigits() {
        String url = "https://www.example.com";
        double expected = 0.0;

        double actual = domainDigitsNumberExtractor.extract(url);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the number of digits in the domain when a valid URL is provided")
    void extractWhenValidUrlIsProvided() {
        String url = "https://www.example123.com";
        double expected = 3.0;

        double actual = domainDigitsNumberExtractor.extract(url);

        assertEquals(expected, actual);
    }

}