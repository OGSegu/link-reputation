package org.reputation.ml.extractor;

import java.util.List;
import java.util.Map;

public interface FeatureExtractorComponent {

    Map<String, Double> extract(String url);

    List<FeatureExtractor> getExtractors();

}
