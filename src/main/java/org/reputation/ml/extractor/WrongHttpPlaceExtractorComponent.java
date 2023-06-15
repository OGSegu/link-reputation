package org.reputation.ml.extractor;

import java.util.regex.Pattern;

public class WrongHttpPlaceExtractorComponent implements FeatureExtractor {

    private static final Pattern HTTP_REGEXP = Pattern.compile("(http)s*");

    @Override
    public double extract(String url) {
        return HTTP_REGEXP.matcher(url).results().count() > 1 ? 1 : 0;
    }

    @Override
    public String featureName() {
        return "wrongHttpPlace";
    }
}
