package org.reputation.ml;

public interface FeatureExtractor {

    double extract(String url);

    String featureName();

}
