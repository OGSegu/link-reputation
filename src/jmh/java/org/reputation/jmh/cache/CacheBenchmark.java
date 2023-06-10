package org.reputation.jmh.cache;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.tuple.Pair;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class CacheBenchmark {

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void put(Blackhole bh, CacheBenchmarkPlan plan) {
        List<Pair<String, Double>> predictions = plan.urlToPrediction;
        for (Pair<String, Double> urlToPrediction : predictions) {
            boolean added = plan.cache.put(urlToPrediction.getKey(), urlToPrediction.getValue());
            bh.consume(added);
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void get(Blackhole bh, CacheBenchmarkPlan plan) {
        List<Pair<String, Double>> predictions = plan.urlToPrediction;
        for (Pair<String, Double> urlToPrediction : predictions) {
            Double result = plan.cache.get(urlToPrediction.getKey());
            bh.consume(result);
        }
    }

}
