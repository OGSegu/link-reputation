package org.reputation.ml.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PathTokensExtractorComponentTest {

    private PathTokensExtractorComponent pathTokensExtractorComponent;

    @BeforeEach
    void setUp() {
        pathTokensExtractorComponent = new PathTokensExtractorComponent();
    }

    @Test
    @DisplayName("Should return the correct count of path tokens in the given URL")
    void extractPathTokensCountFromUrl() {
        String url = "www.example.com/path/to/resource";
        double expectedCount = 3.0;
        double actualCount = pathTokensExtractorComponent.extract(url);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Should return zero when the URL has no path tokens")
    void extractPathTokensCountFromUrlWithNoPathTokens() {
        String url = "www.example.com";
        double expectedPathTokensCount = 0.0;

        double actualPathTokensCount = pathTokensExtractorComponent.extract(url);

        assertEquals(expectedPathTokensCount, actualPathTokensCount);
    }

    @Test
    @DisplayName("Should handle empty or null URL input")
    void extractPathTokensCountFromEmptyOrNullUrl() {// Test for empty URL
        assertEquals(0, pathTokensExtractorComponent.extract(""));

        // Test for null URL
        assertThrows(NullPointerException.class, () -> {
            pathTokensExtractorComponent.extract(null);
        });

        // Test for URL with no path tokens
        assertEquals(0, pathTokensExtractorComponent.extract("www.example.com"));

        // Test for URL with one path token
        assertEquals(1, pathTokensExtractorComponent.extract("www.example.com/path1"));

        // Test for URL with multiple path tokens
        assertEquals(3, pathTokensExtractorComponent.extract("www.example.com/path1/path2/path3"));
    }

}