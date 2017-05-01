package net.mypieceofthe.java8.C2_streams;

import com.google.common.base.Stopwatch;

import java.util.function.UnaryOperator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 01/05/2017.
 *
 * Results on my Macbook Air (1,3 GHz Intel Core i5, JDK 1.8.0_131)
 *
 * For 25_000_000:
 *
 *                  Iterative: 19.73 ms <<< 1
 * Sequential infinite stream: 767.7 ms
 *      Sequential LongStream: 32.01 ms < 3
 *   Parallel infinite stream: 10.18 s
 *        Parallel LongStream: 29.35 ms << 2
 *
 * For 200_000_000 ("Parallel infinite stream" was disabled as it took to long to execute):
 *
 *                  Iterative: 199.9 ms << 2
 * Sequential infinite stream: 5.532 s
 *      Sequential LongStream: 212.0 ms < 3
 *        Parallel LongStream: 116.0 ms <<< 1
 *
 *  For 2_000_000_000
 *  ("Parallel infinite stream" and "Sequential infinite stream" were disabled as they took to long to execute):
 *
 *             Iterative: 1.507 s << 2
 * Sequential LongStream: 1.703 s < 3
 *   Parallel LongStream: 854.5 ms <<< 1
 *
 * For 20_000_000_000L:
 * ("Parallel infinite stream" and "Sequential infinite stream" were disabled as they took to long to execute):
 *
 *             Iterative: 11.04 s << 2
 * Sequential LongStream: 17.65 s < 3
 *   Parallel LongStream: 9.158 s <<< 1
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
