package org.reputation.ml.extractors;

import com.google.common.collect.ImmutableSet;
import org.reputation.ml.FeatureExtractor;

import java.util.Set;

public class SpecialSymbolsCountExtractor implements FeatureExtractor {

    //TODO configured
    private static final Set<Character> SYMBOLS = ImmutableSet.of('?', '.', '%', '=');

    @Override
    public double extract(String url) {
        int counter = 0;
        for (char c : url.toCharArray()) {
            if (SYMBOLS.contains(c)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String featureName() {
        return "specialSymbolsCount";
    }
}
