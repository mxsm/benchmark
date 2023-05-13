package com.github.mxsm.threadlocal;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalIndexOptimize {
    private final Random random = new Random();
    private final ThreadLocal<AtomicInteger> threadLocalIndex = ThreadLocal.withInitial(() -> new AtomicInteger(random.nextInt()));
    private final static int POSITIVE_MASK = 0x7FFFFFFF;

    public int incrementAndGet() {
        return Math.abs(this.threadLocalIndex.get().incrementAndGet() & POSITIVE_MASK);
    }

    @Override
    public String toString() {
        return "ThreadLocalIndex{" +
            "threadLocalIndex=" + threadLocalIndex.get() +
            '}';
    }
}
