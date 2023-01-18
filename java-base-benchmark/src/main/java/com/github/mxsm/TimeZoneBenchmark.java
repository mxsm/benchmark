package com.github.mxsm;

import java.util.concurrent.TimeUnit;
import org.apache.rocketmq.common.BrokerConfig;
import org.apache.rocketmq.store.stats.BrokerStatsManager;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class TimeZoneBenchmark {

    private BrokerStatsManager manager = new BrokerStatsManager(new BrokerConfig());

    @Benchmark
    @Threads(8)
    public void after() {
        manager.incTopicPutLatency("text",100,1);
    }

/*    @Benchmark
    @Threads(8)
    public String build() {

    }*/

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(TimeZoneBenchmark.class.getSimpleName())
            .addProfiler(StackProfiler.class)
            .build();
        new Runner(opt).run();
    }

}
