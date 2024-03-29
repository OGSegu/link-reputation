package org.reputation.jmh.model;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.reputation.ml.exception.ClassificationFailedException;

import java.util.concurrent.TimeUnit;



@State(Scope.Benchmark)
public class ModelBenchmark {

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void modelBench(Blackhole bh, ModelBenchmarkPlan plan) throws ClassificationFailedException {
        for (String url : plan.urls) {
            double result = plan.mlClassifierComponent.classify(url);
            bh.consume(result);
        }
    }
}
