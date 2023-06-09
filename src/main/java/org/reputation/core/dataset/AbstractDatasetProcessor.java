package org.reputation.core.dataset;

import org.reputation.core.FeatureExtractorComponent;
import weka.core.Instances;

import java.util.List;

public abstract class AbstractDatasetProcessor {

    protected final FeatureExtractorComponent featureExtractorComponent;

    protected final int dataPerClazzLimit;
    protected final String dataClazzSeparator;
    protected final String goodClazzText;

    protected AbstractDatasetProcessor(FeatureExtractorComponent featureExtractorComponent,
                                       int dataPerClazzLimit,
                                       String dataClazzSeparator,
                                       String goodClazzText) {
        this.featureExtractorComponent = featureExtractorComponent;
        this.dataPerClazzLimit = dataPerClazzLimit;
        this.dataClazzSeparator = dataClazzSeparator;
        this.goodClazzText = goodClazzText;
    }

    protected abstract Instances getDataSet(List<String> urls);

    public String getDataClazzSeparator() {
        return dataClazzSeparator;
    }

    public String getGoodClazzText() {
        return goodClazzText;
    }
}
