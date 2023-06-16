package org.reputation.ml.extractor;

import org.apache.commons.lang3.StringUtils;
import org.reputation.ml.processor.UrlPreprocessor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FeatureExtractorComponentImpl implements FeatureExtractorComponent {

    private final List<FeatureExtractor> extractors;
    private final UrlPreprocessor preprocessor;

    public FeatureExtractorComponentImpl() {
        this.extractors = List.of(
                new HyphenCountExtractorComponent(),
                new LengthExtractorComponent(),
                new SpecialSymbolsCountExtractorComponent(),
                new WrongHttpPlaceExtractorComponent(),
                new PathTokensExtractorComponent(),
                new IpExtractorComponent(),
                new DigitsNumberExtractorComponent(),
                new DigitToLetterRatioExtractor(),
                new AtSymbolNumberExtractor(),
                new DomainDigitsNumberExtractor(),
                new DomainHyphensNumberExtractor(),
                new DomainLengthExtractor(),
                new DomainNonAlphanumericCharsNumberExtractor(),
                new PercentTwentyNumberExtractor(),
                new QueryArgNumbersExtractor(),
                new SubdomainsNumberExtractor(),
                new UpperToLowerCaseRatioExtractor()
        );
        this.preprocessor = new UrlPreprocessor();
    }

    @Override
    public Map<String, Double> extract(String url) {
        if (StringUtils.isBlank(url)) {
            return Collections.emptyMap();
        }
        String preprocessedUrl = preprocessor.preprocess(url);
        Map<String, Double> result = new LinkedHashMap<>(extractors.size());
        for (FeatureExtractor extractor : extractors) {
            result.put(extractor.featureName(), extractor.extract(preprocessedUrl));
        }
        return result;
    }

    @Override
    public List<FeatureExtractor> getExtractors() {
        return extractors;
    }
}
