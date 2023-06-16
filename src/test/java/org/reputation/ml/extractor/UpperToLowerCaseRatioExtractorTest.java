package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpperToLowerCaseRatioExtractorTest {

    private UpperToLowerCaseRatioExtractor upperToLowerCaseRatioExtractor;

    @BeforeEach
    void setUp() {
        upperToLowerCaseRatioExtractor = new UpperToLowerCaseRatioExtractor();
    }

    @Test
    @DisplayName("Should return 0 when the URL contains no alphabetic characters")
    void extractWhenUrlContainsNoAlphabeticCharacters() {
        String url = "1234567890";
        double result = upperToLowerCaseRatioExtractor.extract(url);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Should return 0 when the URL is empty")
    void extractWhenUrlIsEmpty() {
        String url = "";
        double expectedRatio = 0.0;
        double actualRatio = upperToLowerCaseRatioExtractor.extract(url);
        assertEquals(expectedRatio, actualRatio);
    }

    @Test
    @DisplayName("Should return the correct ratio when the URL contains only upper case characters")
    void extractWhenUrlContainsOnlyUpperCaseCharacters() {
        String url = "EXAMPLEURL";
        double expectedRatio = 10.0;

        double actualRatio = upperToLowerCaseRatioExtractor.extract(url);

        assertEquals(expectedRatio, actualRatio);
    }

    @Test
    @DisplayName("Should return the correct ratio when the URL contains both upper and lower case characters")
    void extractWhenUrlContainsBothUpperAndLowerCaseCharacters() {
        String url = "ExAmPlE.com";
        double expectedRatio = 0;
        double actualRatio = upperToLowerCaseRatioExtractor.extract(url);
        assertEquals(expectedRatio, actualRatio);
    }

    @Test
    @DisplayName("Should return 0 when the URL contains only lower case characters")
    void extractWhenUrlContainsOnlyLowerCaseCharacters() {
        String url = "exampleurl";
        double expectedRatio = 0.0;

        double actualRatio = upperToLowerCaseRatioExtractor.extract(url);

        assertEquals(expectedRatio, actualRatio);
    }

}