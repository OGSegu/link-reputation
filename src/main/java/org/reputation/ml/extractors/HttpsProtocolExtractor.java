package org.reputation.ml.extractors;

import org.reputation.ml.FeatureExtractor;

public class HttpsProtocolExtractor implements FeatureExtractor {

    private static final String HTTPS_PREFIX = "https";

    @Override
    public double extract(String url) {
        return url.startsWith(HTTPS_PREFIX) ? 1 : 0;
    }

    @Override
    public String featureName() {
        return "httpsProtocol";
    }
}
