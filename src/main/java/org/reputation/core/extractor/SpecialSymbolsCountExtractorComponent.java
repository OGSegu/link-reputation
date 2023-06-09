package org.reputation.core.extractor;

import org.reputation.core.FeatureExtractor;

import java.util.Set;

public class SpecialSymbolsCountExtractorComponent implements FeatureExtractor {

    //TODO configured
    private static final Set<Character> SYMBOLS = Set.of('?', '.', '%', '=', '@');

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
