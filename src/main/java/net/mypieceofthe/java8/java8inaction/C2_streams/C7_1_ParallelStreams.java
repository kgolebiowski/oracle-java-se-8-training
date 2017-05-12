package net.mypieceofthe.java8.java8inaction.C2_streams;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 12/05/2017.
 */
public class C7_1_ParallelStreams {
    public static void main(String[] args) {
        new C7_1_ParallelStreams()
                .atomicVarsAndParallelStreams();
    }

    /** OCP Study Guide: Chapter 7 Concurrency, review question 4 */
    private C7_1_ParallelStreams atomicVarsAndParallelStreams() {
        AtomicLong value1 = new AtomicLong(0);
        final long[] value2 = {0};

        IntConsumer atomicLongAdder = i -> value1.incrementAndGet();
        IntConsumer arrayLongAdder = i -> ++value2[0];

        Stream.of(atomicLongAdder, arrayLongAdder).forEach(adder ->
                IntStream.iterate(1, i -> 1)
                        .limit(100)
                        .parallel()
                        .forEach(adder));

        System.out.println("AtomicLong " + value1);
        System.out.println("Regular array value " + value2[0] +
                " (because it's not atomic, prints different value every time is run)");

        return this;
    }
}
