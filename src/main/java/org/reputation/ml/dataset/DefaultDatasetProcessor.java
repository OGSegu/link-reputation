package org.reputation.ml.dataset;

import org.reputation.ml.FeatureExtractor;
import org.reputation.ml.FeatureExtractorComponent;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public class DefaultDatasetProcessor extends AbstractDatasetProcessor {

    public DefaultDatasetProcessor(FeatureExtractorComponent featureExtractorComponent) {
        super(featureExtractorComponent, 200_000, ",", "good");
    }

    public Instances getDataSet(List<String> urls) {

        int benignClazzCounter = 0;
        int maliciousClazzCounter = 0;

        List<FeatureExtractor> extractors = featureExtractorComponent.getExtractors();
        Instances instances = getInstances(extractors);

        for (String urlWithClazz : urls) {

            if (benignClazzCounter >= dataPerClazzLimit && maliciousClazzCounter >= dataPerClazzLimit) {
                break;
            }

            String[] data = urlWithClazz.split(dataClazzSeparator);
            String url = data[0];
            if (data.length == 1) {
                continue;
            }

            String clazz = data[1];
            boolean isBenignClazz = benignClazzText.equalsIgnoreCase(clazz);

            if (isBenignClazz) {
                if (benignClazzCounter <= dataPerClazzLimit) {
                    benignClazzCounter++;
                } else {
                    continue;
                }
            } else {
                if (maliciousClazzCounter <= dataPerClazzLimit) {
                    maliciousClazzCounter++;
                } else {
                    continue;
                }
            }

            DenseInstance instance = getInstance(extractors, instances, url, isBenignClazz);
            instances.add(instance);
        }
        return instances;
    }

    private static DenseInstance getInstance(List<FeatureExtractor> extractors, Instances instances, String url, boolean isBenignClazz) {
        double[] attributesValues = new double[instances.numAttributes()];
        attributesValues[0] = isBenignClazz ? 1 : 0;
        for (int j = 0; j < extractors.size(); j++) {
            FeatureExtractor extractor = extractors.get(j);
            attributesValues[j + 1] = extractor.extract(url);
        }
        return new DenseInstance(1.0, attributesValues);
    }

    private Instances getInstances(List<FeatureExtractor> extractors) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("class"));
        extractors.forEach(extractor -> attributes.add(new Attribute(extractor.featureName())));
        return new Instances("dataset", attributes, 0);
    }
}
