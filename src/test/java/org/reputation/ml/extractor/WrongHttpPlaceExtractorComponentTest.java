package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrongHttpPlaceExtractorComponentTest {

    private WrongHttpPlaceExtractorComponent wrongHttpPlaceExtractorComponent;

    @BeforeEach
    void setUp() {
        wrongHttpPlaceExtractorComponent = new WrongHttpPlaceExtractorComponent();
    }

    @Test
    @DisplayName("Should return 0 when the URL does not contain 'http' or 'https'")
    void extractWhenUrlDoesNotContainHttpOrHttps() {
        String url = "www.example.com";
        double result = wrongHttpPlaceExtractorComponent.extract(url);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Should return 1 when the URL contains more than one 'http' or 'https'")
    void extractWhenUrlContainsMultipleHttpOrHttps() {
        String url = "https://www.example.com/http/https";
        double expected = 1.0;
        double actual = wrongHttpPlaceExtractorComponent.extract(url);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return 0 when the URL contains only one 'http' or 'https'")
    void extractWhenUrlContainsSingleHttpOrHttps() {
        String url = "https://www.example.com";
        double expected = 0;

        double actual = wrongHttpPlaceExtractorComponent.extract(url);

        assertEquals(expected, actual);
    }

}