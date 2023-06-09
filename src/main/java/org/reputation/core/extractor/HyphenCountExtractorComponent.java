package org.reputation.core.extractor;

import com.google.common.base.CharMatcher;
import org.reputation.core.FeatureExtractor;

public class HyphenCountExtractorComponent implements FeatureExtractor {

    @Override
    public double extract(String url) {
        return CharMatcher.is('-').countIn(url);
    }

    @Override
    public String featureName() {
        return "hyphenCount";
    }
}
