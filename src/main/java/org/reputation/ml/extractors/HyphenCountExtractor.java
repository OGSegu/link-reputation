package org.reputation.ml.extractors;

import com.google.common.base.CharMatcher;
import org.reputation.ml.FeatureExtractor;

public class HyphenCountExtractor implements FeatureExtractor {

    @Override
    public double extract(String url) {
        return CharMatcher.is('-').countIn(url);
    }

    @Override
    public String featureName() {
        return "hyphenCount";
    }
}
