package org.reputation.ml.extractor;

import org.reputation.ml.FeatureExtractor;

import java.util.regex.Pattern;

public class NumbersCountExtractorComponent implements FeatureExtractor {

    private static final Pattern NUMBERS_PATTERN = Pattern.compile("\\d");

    @Override
    public double extract(String url) {
        return NUMBERS_PATTERN.matcher(url).results().count();
    }

    @Override
    public String featureName() {
        return "numbersCount";
    }
}
