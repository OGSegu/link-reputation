package org.reputation.core;

import org.reputation.core.extractor.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureExtractorComponentImpl implements FeatureExtractorComponent {

    private final List<FeatureExtractor> extractors;

    public FeatureExtractorComponentImpl() {
        this.extractors = List.of(
                new HttpsProtocolExtractorComponent(),
                new HyphenCountExtractorComponent(),
                new LengthExtractorComponent(),
                new SpecialSymbolsCountExtractorComponent(),
                new WrongHttpPlaceExtractorComponent(),
                new PathTokensExtractorComponent(),
                new IpExtractorComponent(),
                new NumbersCountExtractorComponent()
        );
    }

    @Override
    public Map<String, Double> extract(String url) {
        Map<String, Double> result = new HashMap<>(extractors.size());
        for (FeatureExtractor extractor : extractors) {
            result.put(extractor.featureName(), extractor.extract(url));
        }
        return result;
    }

    @Override
    public List<FeatureExtractor> getExtractors() {
        return extractors;
    }
}
