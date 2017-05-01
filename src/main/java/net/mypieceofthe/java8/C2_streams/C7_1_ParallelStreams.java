package net.mypieceofthe.java8.C2_streams;

import com.google.common.base.Stopwatch;

import java.util.function.UnaryOperator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 01/05/2017.
 *
 * Results on my Macbook Air (1,3 GHz Intel Core i5, JDK 1.8.0_131):
 *
 *                  Iterative: 19.73 ms
 * Sequential infinite stream: 767.7 ms
 *      Sequential LongStream: 32.01 ms
 *   Parallel infinite stream: 10.18 s
 *        Parallel LongStream: 29.35 ms
 *
 */
public class C7_1_ParallelStreams {

    public static void main(String[] args) {
        System.out.println("Tests will be executed using " + Runtime.getRuntime().availableProcessors() + " threads\n");

        final long n = 25_000_000;

        System.out.printf("%30s: %s\n", "Iterative",
                benchmarkExecution(() -> {
                    long result = 0;
                    for (long i = 1L; i <= n; i++) {
                        result += i;
                    }
                })
        );

        UnaryOperator<Long> acc = i -> i + 1;

        System.out.printf("%30s: %s\n", "Sequential infinite stream",
                benchmarkExecution(() ->
                        Stream.iterate(1L, acc)
                                .limit(n)
                                .reduce(0L, Long::sum))
        );

        System.out.printf("%30s: %s\n", "Sequential LongStream",
                benchmarkExecution(() ->
                        LongStream.rangeClosed(1, n)
                                .reduce(0L, Long::sum))
        );

        System.out.printf("%30s: %s\n", "Parallel infinite stream",
                benchmarkExecution(() ->
                        Stream.iterate(1L, acc)
                                .limit(n)
                                .parallel()
                                .reduce(0L, Long::sum))
        );

        System.out.printf("%30s: %s\n", "Parallel LongStream",
                benchmarkExecution(() ->
                        LongStream.rangeClosed(1, n)
                                .parallel()
                                .reduce(0L, Long::sum))
        );
    }

    private static Stopwatch benchmarkExecution(Runnable runnable) {
        Stopwatch sw = Stopwatch.createStarted();
        runnable.run();
        return sw.stop();
    }
}
