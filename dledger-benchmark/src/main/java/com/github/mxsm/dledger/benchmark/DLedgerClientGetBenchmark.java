package com.github.mxsm.dledger.benchmark;

import io.openmessaging.storage.dledger.client.DLedgerClient;
import io.openmessaging.storage.dledger.protocol.AppendEntryResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
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
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
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
public class DLedgerClientGetBenchmark {

    private DLedgerClient client;


    @Setup
    public void init() {
        client = new DLedgerClient("default", "n0-172.28.219.92:8080");
        client.startup();
    }

    @Benchmark
    @Threads(400)
    public void dLedgerClientGet100() {
        client.append(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        //client.get(ThreadLocalRandom.current().nextInt(100));
    }

    @TearDown
    public void shutdown(){
        client.shutdown();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(DLedgerClientGetBenchmark.class.getSimpleName())
            .build();
        new Runner(opt).run();
    }

}
