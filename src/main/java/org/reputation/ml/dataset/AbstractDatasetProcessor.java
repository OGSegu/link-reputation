package org.reputation.ml.dataset;

import org.reputation.ml.FeatureExtractorComponent;
import weka.core.Instances;

import java.util.List;

public abstract class AbstractDatasetProcessor {

    protected final FeatureExtractorComponent featureExtractorComponent;

    protected final int dataPerClazzLimit;
    protected final String dataClazzSeparator;
    protected final String benignClazzText;

    protected AbstractDatasetProcessor(FeatureExtractorComponent featureExtractorComponent,
                                       int dataPerClazzLimit,
                                       String dataClazzSeparator,
                                       String benignClazzText) {
        this.featureExtractorComponent = featureExtractorComponent;
        this.dataPerClazzLimit = dataPerClazzLimit;
        this.dataClazzSeparator = dataClazzSeparator;
        this.benignClazzText = benignClazzText;
    }

    protected abstract Instances getDataSet(List<String> urls);

    public String getDataClazzSeparator() {
        return dataClazzSeparator;
    }

    public String getBenignClazzText() {
        return benignClazzText;
    }
}
