package com.github.mxsm;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ConcurrentHashMapBenchmark {

    private static final String KEY = "mxsm";

    private final Map<String, Object> concurrentMap = new ConcurrentHashMap<>();

    @Setup(Level.Iteration)
    public void setup() {
        concurrentMap.clear();
    }

    @Benchmark
    @Threads(16)
    public Object benchmarkGetBeforeComputeIfAbsent() {
        Object result = concurrentMap.get(KEY);
        if (null == result) {
            result = concurrentMap.computeIfAbsent(KEY, key -> 1);
        }
        return result;
    }

    @Benchmark
    @Threads(16)
    public Object benchmarkComputeIfAbsent() {
        return concurrentMap.computeIfAbsent(KEY, key -> 1);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(ConcurrentHashMapBenchmark.class.getSimpleName())
            .build();
        new Runner(opt).run();
    }

}
