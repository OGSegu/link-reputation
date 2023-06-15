package org.reputation.ml.classifier;

import com.google.common.annotations.VisibleForTesting;
import org.reputation.ml.extractor.FeatureExtractor;
import org.reputation.ml.extractor.FeatureExtractorComponent;
import org.reputation.ml.extractor.FeatureExtractorComponentImpl;
import org.reputation.ml.config.MLClassifierConfig;
import org.reputation.ml.exception.ClassificationFailedException;
import weka.classifiers.Classifier;
import weka.core.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * Only for testing purpose, don't use it in your logic
     */
    @VisibleForTesting
    public MLClassifierComponentImpl(InputStream modelStream) throws Exception {
        this.config = null;
        this.classifier = (Classifier) SerializationHelper.read(modelStream);
        this.featureExtractorComponent = new FeatureExtractorComponentImpl();
    }

    @Override
    public double classify(String url) throws ClassificationFailedException {
        try {
            Instances dataset = getDataset();
            Instance data = from(url);
            data.setDataset(dataset);
            data.setClassMissing();
            dataset.add(data);
            double[] distribution = classifier.distributionForInstance(dataset.instance(0));
            return distribution[1];
        } catch (Exception e) {
            throw new ClassificationFailedException(url, e);
        }
    }

    private Instances getDataset() {
        List<FeatureExtractor> extractors = featureExtractorComponent.getExtractors();
        ArrayList<Attribute> attributes = new ArrayList<>();
        extractors.forEach(extractor -> attributes.add(new Attribute(extractor.featureName())));
        attributes.add(new Attribute("class", Arrays.asList("good", "malicious")));
        Instances instances = new Instances("dataset", attributes, 0);
        instances.setClassIndex(instances.numAttributes() - 1);
        return instances;
    }

    private Instance from(String url) {
        Map<String, Double> featuresToValue = featureExtractorComponent.extract(url);
        double[] features = new double[featuresToValue.size() + 1];
        AtomicInteger idx = new AtomicInteger(0);
        featuresToValue.forEach((name,value)
                -> features[idx.getAndIncrement()] = value == -1
                        ? Utils.missingValue()
                        : value);
        return new DenseInstance(1.0, features);
    }
}
