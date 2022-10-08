package com.github.mxsm.mapstruct;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.BeanUtils;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class MapstructBenchmark {

    private ClassA classA;

    @Setup
    public void init() {
        classA = new ClassA();
    }

    @Benchmark
    public void mapstructBenchmark() {
        ClassA a = StructMapper.INSTANCE.classA2classA(classA);
    }

    @Benchmark
    public void beanUtilsBenchmark() {
        ClassA target = new ClassA();
        BeanUtils.copyProperties(classA, target);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(MapstructBenchmark.class.getSimpleName())
            .result("result.json")
            .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
