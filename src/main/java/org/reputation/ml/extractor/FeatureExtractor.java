package org.reputation.ml.extractor;

public interface FeatureExtractor {

    double extract(String url);

    String featureName();

}
