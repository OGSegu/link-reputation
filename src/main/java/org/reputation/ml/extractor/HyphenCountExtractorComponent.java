package org.reputation.ml.extractor;

import com.google.common.base.CharMatcher;

public class HyphenCountExtractorComponent implements FeatureExtractor {

    @Override
    public double extract(String url) {
        return CharMatcher.is('-').countIn(url);
    }

    @Override
    public String featureName() {
        return "hyphenCount";
    }
}
