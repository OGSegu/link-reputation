package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IpExtractorComponentTest {

    private IpExtractorComponent ipExtractorComponent;

    @BeforeEach
    void setUp() {
        ipExtractorComponent = new IpExtractorComponent();
    }

    @Test
    @DisplayName("Should return 0 when the URL does not contain an IP address")
    void extractWhenUrlDoesNotContainIpAddress() {
        String url = "https://www.example.com";
        double expected = 0;

        double actual = ipExtractorComponent.extract(url);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return 1 when the URL contains an IP address")
    void extractWhenUrlContainsIpAddress() {
        String url = "http://192.168.0.1/index.html";
        double expected = 1.0;
        double actual = ipExtractorComponent.extract(url);
        assertEquals(expected, actual);
    }

}