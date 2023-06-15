package org.reputation.ml.extractor;

import java.util.regex.Pattern;

public class DigitsNumberExtractorComponent implements FeatureExtractor {

    private static final Pattern DIGITS_NUMBER = Pattern.compile("\\d");

    @Override
    public double extract(String url) {
        return DIGITS_NUMBER.matcher(url).results().count();
    }

    @Override
    public String featureName() {
        return "digitsNumber";
    }
}
