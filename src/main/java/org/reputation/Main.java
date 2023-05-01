package org.reputation;

import org.reputation.ml.FeatureExtractorComponent;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> result = Files.readAllLines(Path.of("./data.txt")).stream().limit(300000).collect(Collectors.toList());
        System.out.println(result.size());
        FeatureExtractorComponent extractor = new FeatureExtractorComponent();
        String transformed = extractor.extractAll(result);
        Files.writeString(Path.of("./transformed.txt"), transformed);



//        Logistic logistic = (Logistic) SerializationHelper.read("./logisticIris.model");
//        ConverterUtils.DataSource datasource = new ConverterUtils.DataSource("./iris.arff");
//        Instances dataSet = datasource.getDataSet();
//        dataSet.setClassIndex(dataSet.numAttributes() - 1);
//        Instance instance = dataSet.instance(59);
//        double classValue = instance.classValue();
//        double pred = logistic.classifyInstance(instance);
//        double percent = Arrays.stream(logistic.distributionForInstance(instance)).max().getAsDouble();
//        System.out.println("Actual: " + classValue + " | Prediction: " + pred + " | Percent: " + percent);
    }
}