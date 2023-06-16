package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitToLetterRatioExtractorTest {

    private DigitToLetterRatioExtractor digitToLetterRatioExtractor;

    @BeforeEach
    void setUp() {
        digitToLetterRatioExtractor = new DigitToLetterRatioExtractor();
    }

    @Test
    @DisplayName("Should return 0 when the URL contains only letters")
    void extractWhenUrlContainsOnlyLetters() {
        String url = "exampleurl";
        double expectedRatio = 0.0;

        double actualRatio = digitToLetterRatioExtractor.extract(url);

        assertEquals(expectedRatio, actualRatio);
    }

    @Test
    @DisplayName("Should return the correct ratio when the URL contains only digits")
    void extractWhenUrlContainsOnlyDigits() {
        String url = "1234567890";
        double expectedRatio = 10.0;

        double actualRatio = digitToLetterRatioExtractor.extract(url);

        assertEquals(expectedRatio, actualRatio, 0.01);
    }

    @Test
    @DisplayName("Should return the correct ratio when the URL contains both digits and letters")
    void extractWhenUrlContainsDigitsAndLetters() {
        String url = "abc123def456";
        double expectedRatio = 1;

        double actualRatio = digitToLetterRatioExtractor.extract(url);

        assertEquals(expectedRatio, actualRatio);
    }

    @Test
    @DisplayName("Should return 0 when the URL is empty or contains no digits and letters")
    void extractWhenUrlIsEmptyOrNoDigitsAndLetters() {
        String emptyUrl = "";
        String urlWithNoDigitsAndLetters = "!@#$%^&*()_+";

        double emptyUrlRatio = digitToLetterRatioExtractor.extract(emptyUrl);
        double urlWithNoDigitsAndLettersRatio = digitToLetterRatioExtractor.extract(urlWithNoDigitsAndLetters);

        assertEquals(0, emptyUrlRatio);
        assertEquals(0, urlWithNoDigitsAndLettersRatio);
    }

}