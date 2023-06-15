package org.reputation.ml.extractor;

import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicInteger;

public class DomainNonAlphanumericCharsNumberExtractor implements FeatureExtractor, DomainExtractor{
    @Override
    public double extract(String url) {
        String domain;
        try {
            domain = getDomain(url);
        } catch (Exception e) {
            return -1;
        }
        AtomicInteger counter = new AtomicInteger(0);
        domain.chars().forEach(currentChar -> {
            if (!Character.isLetterOrDigit(currentChar)) {
                counter.incrementAndGet();
            }
        });
        return counter.get();
    }

    @Override
    public String featureName() {
        return "domainNonAlphanumericNumber";
    }
}
