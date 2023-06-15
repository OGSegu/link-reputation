package org.reputation.ml.extractor;

import java.net.MalformedURLException;

public class DomainLengthExtractor implements FeatureExtractor, DomainExtractor {
    @Override
    public double extract(String url) {
        String domain;
        try {
            domain = getDomain(url);
        } catch (Exception e) {
            return -1;
        }
        return domain.length();
    }

    @Override
    public String featureName() {
        return "domainLength";
    }
}
