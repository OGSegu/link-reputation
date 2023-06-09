package org.reputation.core.extractor;

import org.reputation.core.FeatureExtractor;

public class LengthExtractorComponent implements FeatureExtractor {
    @Override
    public double extract(String url) {
        return url.length();
    }

    @Override
    public String featureName() {
        return "urlLength";
    }
}
