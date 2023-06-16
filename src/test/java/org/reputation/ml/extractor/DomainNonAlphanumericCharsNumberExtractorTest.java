package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DomainNonAlphanumericCharsNumberExtractorTest {

    @Spy
    private DomainNonAlphanumericCharsNumberExtractor extractor;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should return -1 when the URL is invalid")
    void extractReturnsMinusOneWhenUrlIsInvalid() {
        String invalidUrl = "invalid url";
        double result = extractor.extract(invalidUrl);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Should return the number of non-alphanumeric characters in the domain")
    void extractReturnsNumberOfNonAlphanumericCharsInDomain() throws MalformedURLException {
        String url = "https://www.example.com";
        String domain = "example.com";
        when(extractor.getDomain(url)).thenReturn(domain);

        double result = extractor.extract(url);

        assertEquals(1, result);
    }

}