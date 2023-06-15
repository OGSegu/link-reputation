package org.reputation.ml.extractor;

import com.google.common.base.CharMatcher;

public class PathTokensExtractorComponent implements FeatureExtractor {
    @Override
    public double extract(String url) {
        return CharMatcher.anyOf("/").countIn(url);
    }

    @Override
    public String featureName() {
        return "pathTokens";
    }
}
