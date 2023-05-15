package org.reputation.jmh;


import org.openjdk.jmh.annotations.*;
import org.reputation.ml.classifier.MLClassifierComponent;
import org.reputation.ml.classifier.MLClassifierComponentImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
public class ModelBenchmarkPlan {

    private static final int LIMIT_URLS = 100_000;

    public List<String> urls;

    public MLClassifierComponent mlClassifierComponent;

    @Param({"bayess.model", "adaboostm1.model", "logistic.model", "rndforest.model", "sgd.model", "perceptron.model"})
    public String modelName;


    @Setup(Level.Trial)
    public void setUp() throws Exception {

        InputStream datasetStream = getClass().getClassLoader().getResourceAsStream("data.txt");
        List<String> parsedUrls = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(datasetStream))) {
            for (int i = 0; i < LIMIT_URLS; i++) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String url = line.split("\t")[0];
                parsedUrls.add(url);
            }
        }
        this.urls = parsedUrls;

        InputStream modelStream = getClass().getClassLoader().getResourceAsStream(modelName);
        this.mlClassifierComponent = new MLClassifierComponentImpl(modelStream);
    }

}
