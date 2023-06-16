package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureExtractorComponentImplTest {

    private FeatureExtractorComponent featureExtractorComponent;

    @BeforeEach
    void setUp() {
        featureExtractorComponent = new FeatureExtractorComponentImpl();
    }

    @Test
    @DisplayName("Should return an empty map when the input URL is empty or null")
    void extractFeaturesFromEmptyOrNullUrl() {
        String emptyUrl = "";
        String nullUrl = null;

        Map<String, Double> emptyResult = featureExtractorComponent.extract(emptyUrl);
        Map<String, Double> nullResult = featureExtractorComponent.extract(nullUrl);

        assertTrue(emptyResult.isEmpty());
        assertTrue(nullResult.isEmpty());
    }

    @Test
    @DisplayName("Should return a map with extracted features from the given URL")
    void extractFeaturesFromUrl() {
        String url = "https://www.example.com/path/to/page?query=string";
        Map<String, Double> actualFeatures = featureExtractorComponent.extract(url);
        for (FeatureExtractor extractor : featureExtractorComponent.getExtractors()) {
            assertTrue(actualFeatures.containsKey(extractor.featureName()));
        }
    }

    @Test
    @DisplayName("Should return a map with correct feature names and values for a valid URL")
    void extractFeaturesWithCorrectFeatureNamesAndValues() {
        String url = "https://www.example.com/path/to/page?query=string";
        Map<String, Double> expectedFeatures = new LinkedHashMap<>();
        expectedFeatures.put("HyphenCount", 0.0);
        expectedFeatures.put("Length", 36.0);
        expectedFeatures.put("SpecialSymbolsCount", 3.0);
        expectedFeatures.put("WrongHttpPlace", 0.0);
        expectedFeatures.put("PathTokens", 3.0);
        expectedFeatures.put("Ip", 0.0);
        expectedFeatures.put("DigitsNumber", 0.0);
        expectedFeatures.put("DigitToLetterRatio", 0.0);
        expectedFeatures.put("AtSymbolNumber", 0.0);
        expectedFeatures.put("DomainDigitsNumber", 0.0);
        expectedFeatures.put("DomainHyphensNumber", 1.0);
        expectedFeatures.put("DomainLength", 11.0);
        expectedFeatures.put("DomainNonAlphanumericCharsNumber", 0.0);
        expectedFeatures.put("PercentTwentyNumber", 1.0);
        expectedFeatures.put("QueryArgNumbers", 1.0);
        expectedFeatures.put("SubdomainsNumber", 2.0);
        expectedFeatures.put("UpperToLowerCaseRatio", 0.25);

        Map<String, Double> actualFeatures = featureExtractorComponent.extract(url);

        assertEquals(expectedFeatures.size(), actualFeatures.size());
    }

}