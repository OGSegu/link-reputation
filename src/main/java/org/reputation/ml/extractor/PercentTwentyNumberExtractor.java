package org.reputation.ml.extractor;

import java.util.regex.Pattern;

public class PercentTwentyNumberExtractor implements FeatureExtractor, DomainExtractor {

    private static final Pattern AT_SYMBOL_TWENTY_PATTERN = Pattern.compile("%20");

    @Override
    public double extract(String url) {
        return AT_SYMBOL_TWENTY_PATTERN.matcher(url).results().count();
    }

    @Override
    public String featureName() {
        return "percentTwentyNumber";
    }
}