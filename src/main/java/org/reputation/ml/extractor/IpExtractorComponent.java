package org.reputation.ml.extractor;

import java.util.regex.Pattern;

public class IpExtractorComponent implements FeatureExtractor {

    private static final Pattern IP_PATTERN = Pattern.compile("(?:\\d{1,3}\\.){3}\\d{1,3}");

    @Override
    public double extract(String url) {
        return IP_PATTERN.matcher(url).find() ? 1 : 0;
    }

    @Override
    public String featureName() {
        return "ip";
    }
}
