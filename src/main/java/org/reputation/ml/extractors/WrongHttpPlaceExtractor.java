package org.reputation.ml.extractors;

import org.reputation.ml.FeatureExtractor;

public class WrongHttpPlaceExtractor implements FeatureExtractor {

    private static final String HTTPS_TEXT = "https";
    private static final String HTTP_TEXT = "http";

    @Override
    public double extract(String url) {
        return (url.contains(HTTP_TEXT) || url.contains(HTTPS_TEXT))
                ? 1
                : 0;
    }

    @Override
    public String featureName() {
        return "wrongHttpPlace";
    }
}
