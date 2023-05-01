package org.reputation.ml.extractors;

import org.reputation.ml.FeatureExtractor;

public class LengthExtractor implements FeatureExtractor {
    @Override
    public double extract(String url) {
        return url.length();
    }

    @Override
    public String featureName() {
        return "urlLength";
    }
}
