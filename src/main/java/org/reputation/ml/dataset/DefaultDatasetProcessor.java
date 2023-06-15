package org.reputation.ml.dataset;

import org.reputation.ml.extractor.FeatureExtractor;
import org.reputation.ml.extractor.FeatureExtractorComponent;
import org.reputation.ml.processor.UrlPreprocessor;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultDatasetProcessor extends AbstractDatasetProcessor {

    private final UrlPreprocessor preprocessor = new UrlPreprocessor();

    public DefaultDatasetProcessor(FeatureExtractorComponent featureExtractorComponent) {
        super(featureExtractorComponent, 200_000, "\t", "benign");
    }

    public Instances getDataSet(List<String> urls) {

        int goodClazzCounter = 0;
        int maliciousClazzCounter = 0;

        List<FeatureExtractor> extractors = featureExtractorComponent.getExtractors();
        Instances dataset = getInstances(extractors);

        for (String urlWithClazz : urls) {

            if (goodClazzCounter >= dataPerClazzLimit && maliciousClazzCounter >= dataPerClazzLimit) {
                break;
            }

            String[] data = urlWithClazz.split(dataClazzSeparator);
            String url = data[0];
            if (data.length == 1) {
                continue;
            }

            String clazz = data[1];
            boolean isGoodClazz = goodClazzText.equalsIgnoreCase(clazz);

            if (isGoodClazz) {
                if (goodClazzCounter <= dataPerClazzLimit) {
                    goodClazzCounter++;
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

            DenseInstance instance = getInstance(extractors, dataset, url, isGoodClazz);
            dataset.add(instance);
        }
        return dataset;
    }

    private DenseInstance getInstance(List<FeatureExtractor> extractors,
                                             Instances dataset,
                                             String url,
                                             boolean isGoodClazz) {
        DenseInstance instance = new DenseInstance(dataset.numAttributes());
        instance.setDataset(dataset);
        String preprocessedUrl = preprocessor.preprocess(url);
        for (int i = 0; i < extractors.size(); i++) {
            FeatureExtractor extractor = extractors.get(i);
            double value = extractor.extract(preprocessedUrl);
//            if (value == -1) {
//                System.out.printf("Extractor: [%s] failed to extract from url: [%s]%n",
//                        extractor.featureName(), preprocessedUrl);
//            }
            instance.setValue(i, value == -1 ? Utils.missingValue() : value);
        }
        instance.setValue(dataset.numAttributes() - 1, isGoodClazz ? "good" : "malicious");
        return instance;
    }

    private Instances getInstances(List<FeatureExtractor> extractors) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        extractors.forEach(extractor -> attributes.add(new Attribute(extractor.featureName())));
        attributes.add(new Attribute("class", Arrays.asList("good", "malicious")));
        return new Instances("dataset", attributes, 0);
    }
}
