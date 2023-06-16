package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryArgNumbersExtractorTest {

    private QueryArgNumbersExtractor queryArgNumbersExtractor;

    @BeforeEach
    void setUp() {
        queryArgNumbersExtractor = new QueryArgNumbersExtractor();
    }

    @Test
    @DisplayName("Should return 0 when the URL has no query parameters")
    void extractWhenUrlHasNoQueryParameters() {
        String url = "http://example.com";
        double result = queryArgNumbersExtractor.extract(url);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Should return the correct count of query parameters when the URL has query parameters")
    void extractWhenUrlHasQueryParameters() {
        String url = "http://example.com?param1=value1&param2=value2&param3=value3";
        double expectedCount = 2.0;

        double actualCount = queryArgNumbersExtractor.extract(url);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Should return the correct count of query parameters when the URL has a mix of valid and invalid query parameters")
    void extractWhenUrlHasMixOfValidAndInvalidQueryParameters() {
        String url = "http://example.com?param1=value1&param2&param3=value3&param4=";
        double expectedCount = 3.0;

        double actualCount = queryArgNumbersExtractor.extract(url);

        assertEquals(expectedCount, actualCount);
    }

}