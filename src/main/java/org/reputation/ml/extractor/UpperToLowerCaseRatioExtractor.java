package org.reputation.ml.extractor;

import java.util.concurrent.atomic.AtomicInteger;

public class UpperToLowerCaseRatioExtractor implements FeatureExtractor {
    @Override
    public double extract(String url) {
        AtomicInteger upperCaseAmount = new AtomicInteger(0);
        AtomicInteger lowerCaseAmount = new AtomicInteger(0);
        url.chars().forEach(currentChar -> {
            if (Character.isUpperCase(currentChar)) {
                upperCaseAmount.incrementAndGet();
            } else if (Character.isLowerCase(currentChar)) {
                lowerCaseAmount.incrementAndGet();
            }
        });
        return upperCaseAmount.get() / Math.max(lowerCaseAmount.get(), 1);
    }

    @Override
    public String featureName() {
        return "upperToLowerCaseRatio";
    }
}
