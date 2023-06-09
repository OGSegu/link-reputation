package org.reputation.core.extractor;

import org.reputation.core.FeatureExtractor;

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
