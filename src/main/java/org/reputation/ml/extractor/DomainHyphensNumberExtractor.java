package org.reputation.ml.extractor;

import com.google.common.base.CharMatcher;

import java.net.MalformedURLException;

public class DomainHyphensNumberExtractor implements FeatureExtractor, DomainExtractor{

    @Override
    public double extract(String url) {
        String domain;
        try {
            domain = getDomain(url);
        } catch (Exception e) {
            return -1;
        }
        return CharMatcher.is('-').countIn(domain);
    }

    @Override
    public String featureName() {
        return "domainHyphensNumber";
    }
}
