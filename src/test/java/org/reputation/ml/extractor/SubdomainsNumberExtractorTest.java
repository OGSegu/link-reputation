package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubdomainsNumberExtractorTest {

    private SubdomainsNumberExtractor subdomainsNumberExtractor;

    @BeforeEach
    void setUp() {
        subdomainsNumberExtractor = new SubdomainsNumberExtractor();
    }

    @Test
    @DisplayName("Should return -1 for an invalid URL")
    void extractSubdomainsNumberForInvalidUrl() {
        String invalidUrl = "invalid url";
        double expected = -1;

        double actual = subdomainsNumberExtractor.extract(invalidUrl);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the number of subdomains for a valid URL")
    void extractSubdomainsNumberForValidUrl() {
        String url = "https://www.example.com/subdomain1/subdomain2";
        double expectedSubdomainsNumber = 3;

        double actualSubdomainsNumber = subdomainsNumberExtractor.extract(url);

        assertEquals(expectedSubdomainsNumber, actualSubdomainsNumber);
    }

}