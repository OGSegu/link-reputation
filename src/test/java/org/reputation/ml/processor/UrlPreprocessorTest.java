package org.reputation.ml.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlPreprocessorTest {

    private UrlPreprocessor urlPreprocessor = new UrlPreprocessor();

    @Test
    @DisplayName("Should return the same URL if no preprocessing is needed")
    void preprocessReturnsSameUrlIfNoPreprocessingNeeded() {
        String url = "www.example.com";
        String preprocessedUrl = urlPreprocessor.preprocess(url);
        assertEquals(url, preprocessedUrl);
    }

    @Test
    @DisplayName("Should remove http protocol from the URL")
    void preprocessRemovesHttpProtocol() {
        String url = "http://www.example.com";
        String expected = "www.example.com";
        String actual = urlPreprocessor.preprocess(url);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should remove https protocol from the URL")
    void preprocessRemovesHttpsProtocol() {
        String url = "https://www.example.com";
        String expected = "www.example.com";
        String actual = urlPreprocessor.preprocess(url);
        assertEquals(expected, actual);
    }

}