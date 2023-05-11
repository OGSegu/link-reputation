package org.reputation.ml.classifier;

import org.reputation.ml.FeatureExtractor;
import org.reputation.ml.FeatureExtractorComponent;
import org.reputation.ml.FeatureExtractorComponentImpl;
import org.reputation.ml.config.MLClassifierConfig;
import weka.classifiers.Classifier;
import weka.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MLClassifierComponentImpl implements MLClassifierComponent {

    private final MLClassifierConfig config;
    private final Classifier classifier;
    private final FeatureExtractorComponent featureExtractorComponent;

    public MLClassifierComponentImpl(MLClassifierConfig config) throws Exception {
        this.config = config;
        this.classifier = (Classifier) SerializationHelper.read(config.getModelPath());
        this.featureExtractorComponent = new FeatureExtractorComponentImpl();
    }

    @Override
    public double classify(String url) {
        try {
            Instances dataset = getDataset();
            Instance data = from(url);
            data.setDataset(dataset);
            data.setClassMissing();
            dataset.add(data);
            return 1 - classifier.classifyInstance(dataset.instance(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Instances getDataset() {
        List<FeatureExtractor> extractors = featureExtractorComponent.getExtractors();
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("class"));
        extractors.forEach(extractor -> attributes.add(new Attribute(extractor.featureName())));
//        System.out.printf("Attributes added: [%s]%n", attributes);
        Instances instances = new Instances("dataset", attributes, 0);
        instances.setClassIndex(0);
        return instances;
    }

    private Instance from(String url) {
        Map<String, Double> featuresToValue = featureExtractorComponent.extract(url);
//        System.out.printf("Extracted features: [%s]%n",
//                Joiner.on(",").withKeyValueSeparator("=").join(featuresToValue));
        // features[0] is a class attribute
        double[] features = new double[featuresToValue.size() + 1];
        AtomicInteger idx = new AtomicInteger(1);
        featuresToValue.forEach((name,value)
                -> features[idx.getAndIncrement()] = value);
        return new DenseInstance(1.0, features);
    }
}
