package net.mypieceofthe.java8.C3_EffectiveJava8;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 05/05/2017.
 */
public class C11_CompletableFutures {
    private Executor executor = Executors.newCachedThreadPool(this::getThreadFactory);

    public static void main(String[] args) {
        new C11_CompletableFutures().execute();
    }

    private void execute() {
        System.out.println("Running with " + Runtime.getRuntime().availableProcessors() + " threads");

        List<CompletableFuture<Void>> futures =
                Stream.generate(SomeComputingClass::new).limit(100)
                .map(computer -> CompletableFuture.supplyAsync(computer::compute, executor))
                .map(cf -> cf.thenAccept(s -> System.out.println("Finished: " + s)))
                .collect(Collectors.toList());

        System.out.println("Generated and waiting");

        futures.forEach(CompletableFuture::join);

        System.out.println("END");
    }

    private Thread getThreadFactory(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

class SomeComputingClass {
    String compute() {
        long sleepTime = 1000L + new Random().nextInt(1000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("My name is '%s' and I was sleeping for '%d'",
                Thread.currentThread().getName(), sleepTime);
    }
}