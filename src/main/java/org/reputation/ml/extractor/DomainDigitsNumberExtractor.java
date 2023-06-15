package org.reputation.ml.extractor;

import java.net.MalformedURLException;

public class DomainDigitsNumberExtractor implements FeatureExtractor, DomainExtractor {

    private static final DigitsNumberExtractorComponent numbersExtractor = new DigitsNumberExtractorComponent();
    @Override
    public double extract(String url) {
        String domain;
        try {
            domain = getDomain(url);
        } catch (Exception e) {
            return -1;
        }
        return numbersExtractor.extract(domain);
    }

    @Override
    public String featureName() {
        return "domainDigitsNumber";
    }
}
