package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DomainHyphensNumberExtractorTest {

    @Mock
    private DomainExtractor domainExtractor;

    private DomainHyphensNumberExtractor domainHyphensNumberExtractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        domainHyphensNumberExtractor = new DomainHyphensNumberExtractor();
    }

    @Test
    @DisplayName("Should return -1 when the URL is invalid")
    void extractWhenUrlIsInvalid() {
        String invalidUrl = "inval_dasx%12idUrl";
        double expected = -1;
        double actual = domainHyphensNumberExtractor.extract(invalidUrl);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return the number of hyphens in the domain when the URL is valid")
    void extractWhenUrlIsValid() throws MalformedURLException {
        String url = "https://www.example-domain.com";
        double expected = 1;

        when(domainExtractor.getDomain(url)).thenReturn("example-domain.com");

        double actual = domainHyphensNumberExtractor.extract(url);

        assertEquals(expected, actual);
    }

}