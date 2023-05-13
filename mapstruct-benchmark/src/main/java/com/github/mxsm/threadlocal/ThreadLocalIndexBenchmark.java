package com.github.mxsm.threadlocal;

import org.apache.rocketmq.client.common.ThreadLocalIndex;

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

import com.github.mxsm.mapstruct.ClassA;
import com.github.mxsm.mapstruct.StructMapper;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ThreadLocalIndexBenchmark {

    private ThreadLocalIndex index = new ThreadLocalIndex();

    private ThreadLocalIndexOptimize optimize = new ThreadLocalIndexOptimize();

    @Benchmark
    @Threads(1)
    public void threadLocalIndex_1() {
        index.incrementAndGet();
    }

    @Benchmark
    @Threads(1)
    public void threadLocalIndexOptimize_1() {
        optimize.incrementAndGet();
    }

    @Benchmark
    @Threads(8)
    public void threadLocalIndex_8() {
        index.incrementAndGet();
    }

    @Benchmark
    @Threads(8)
    public void threadLocalIndexOptimize_8() {
        optimize.incrementAndGet();
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(ThreadLocalIndexBenchmark.class.getSimpleName())
            .result("result.json")
            .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
