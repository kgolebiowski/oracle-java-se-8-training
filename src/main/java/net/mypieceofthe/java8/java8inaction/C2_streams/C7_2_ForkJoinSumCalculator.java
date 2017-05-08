package net.mypieceofthe.java8.java8inaction.C2_streams;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by kgolebiowski on 03/05/2017.
 */
public class C7_2_ForkJoinSumCalculator extends RecursiveTask<Long> {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static final long THRESHOLD = 10_000;

    private final long[] numbers;
    private final int start;
    private final int end;

    public C7_2_ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private C7_2_ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        C7_2_ForkJoinSumCalculator leftTask = new C7_2_ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        C7_2_ForkJoinSumCalculator rightTask = new C7_2_ForkJoinSumCalculator(numbers, start + length/2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        // This operation takes most of the time for this whole method
        long[] numbers = LongStream.rangeClosed(1, n).toArray();

        ForkJoinTask<Long> task = new C7_2_ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }
    
}
