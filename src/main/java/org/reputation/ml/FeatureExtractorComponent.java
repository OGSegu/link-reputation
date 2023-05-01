package org.reputation.ml;

import org.reputation.ml.extractors.HttpsProtocolExtractor;
import org.reputation.ml.extractors.HyphenCountExtractor;
import org.reputation.ml.extractors.LengthExtractor;
import org.reputation.ml.extractors.SpecialSymbolsCountExtractor;

import java.util.List;

public class FeatureExtractorComponent {

    public static final String SEPARATOR = ";";
    private final List<FeatureExtractor> extractors;

    public FeatureExtractorComponent() {
        this.extractors = List.of(
                new HttpsProtocolExtractor(),
                new HyphenCountExtractor(),
                new LengthExtractor(),
                new SpecialSymbolsCountExtractor()
//                new WrongHttpPlaceExtractor()
        );
    }


    public String extract(String url) {
        StringBuilder sb = new StringBuilder();
        extractors.forEach(extractor -> sb.append(extractor.extract(url)));
        return "";
    }

    public String extractAll(List<String> urls) {
        StringBuilder sb = new StringBuilder();
        extractors.forEach(extractor -> sb.append(extractor.featureName()).append(SEPARATOR));
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        for (String urlWithClass : urls) {
            String[] data = urlWithClass.split("\t");
            String url = data[0];
            String clazz = data[1];
            sb.append(url).append(SEPARATOR + " ");
            extractors.forEach(extractor -> sb.append(extractor.extract(url)).append(SEPARATOR + " "));
            sb.append(clazz).append("\n");
        }
        return sb.toString();
    }
}
