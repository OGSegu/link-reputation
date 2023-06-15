package org.reputation.ml.extractor;

import java.util.concurrent.atomic.AtomicInteger;

public class DigitToLetterRatioExtractor implements FeatureExtractor {
    @Override
    public double extract(String url) {
        AtomicInteger digitsAmount = new AtomicInteger(0);
        AtomicInteger lettersAmount = new AtomicInteger(0);
        url.chars().forEach(currentChar -> {
            if (Character.isDigit(currentChar)) {
                digitsAmount.incrementAndGet();
            } else if (Character.isAlphabetic(currentChar)) {
                lettersAmount.incrementAndGet();
            }
        });
        return digitsAmount.get() / Math.max(lettersAmount.get(), 1);
    }

    @Override
    public String featureName() {
        return "digitToLetterRatio";
    }
}
