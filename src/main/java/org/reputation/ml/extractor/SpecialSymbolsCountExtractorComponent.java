package org.reputation.ml.extractor;

import java.util.Set;

public class SpecialSymbolsCountExtractorComponent implements FeatureExtractor {

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
