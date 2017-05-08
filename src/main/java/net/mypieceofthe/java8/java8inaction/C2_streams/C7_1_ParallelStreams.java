package net.mypieceofthe.java8.java8inaction.C2_streams;

import com.google.common.base.Stopwatch;

import java.util.LinkedHashMap;
import java.util.Map;
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
    private final long maxNumber = 25_000_000;
    private final Map<String, Runnable> testsMap = new LinkedHashMap<>();

    private C7_1_ParallelStreams() {
        System.out.println("Tests will be executed using " + Runtime.getRuntime().availableProcessors() + " threads\n");
    }

    public static void main(String[] args) {
        new C7_1_ParallelStreams()
                .setUpTests()
                .executeTestsAndPrintResults();
    }

    private C7_1_ParallelStreams setUpTests() {
        testsMap.put("Iterative", () -> {
            long result = 0;
            for (long i = 1L; i <= maxNumber; i++) {
                result += i;
            }
        });

        testsMap.put("Sequential infinite stream",
                () -> Stream.iterate(1L, i -> i + 1)
                        .limit(maxNumber)
                        .reduce(0L, Long::sum));

        testsMap.put("Sequential LongStream",
                () -> LongStream.rangeClosed(1, maxNumber)
                        .reduce(0L, Long::sum));

        testsMap.put("Parallel infinite stream",
                () -> Stream.iterate(1L, i -> i + 1)
                        .limit(maxNumber)
                        .parallel()
                        .reduce(0L, Long::sum));

        testsMap.put("Parallel LongStream",
                () -> LongStream.rangeClosed(1, maxNumber)
                        .parallel()
                        .reduce(0L, Long::sum));

        testsMap.put("Custom ForkJoin",
                () -> C7_2_ForkJoinSumCalculator.forkJoinSum(maxNumber));

        return this;
    }

    private void executeTestsAndPrintResults() {
        testsMap.forEach((name, test) ->
                System.out.printf("%30s: %s\n", name, runAndBenchmarkSingleTest(test)));

    }

    private Stopwatch runAndBenchmarkSingleTest(Runnable runnable) {
        Stopwatch sw = Stopwatch.createStarted();
        runnable.run();
        return sw.stop();
    }
}
