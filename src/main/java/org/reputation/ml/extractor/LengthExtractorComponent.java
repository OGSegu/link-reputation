package org.reputation.ml.extractor;

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
