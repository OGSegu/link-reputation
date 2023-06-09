package org.reputation.core;

public interface FeatureExtractor {

    double extract(String url);

    String featureName();

}
