package net.mypieceofthe.java8.C3_EffectiveJava8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by kgolebiowski on 05/05/2017.
 */
public class C11_CompletableFutures {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new C11_CompletableFutures().execute();
    }

    private void execute() throws ExecutionException, InterruptedException {
        System.out.println("MAIN Start " + Thread.currentThread().getName());


        CompletableFuture<String> cf =
                CompletableFuture.supplyAsync(this::computeSomething)
                        .thenApply(s -> "The result: " + s);

        System.out.println(cf.get());

        System.out.println("MAIN End");
    }

    private String computeSomething() {
        try {
            System.out.println("RUN Start " + Thread.currentThread().getName());
            Thread.sleep(1000L);
            System.out.println("RUN End");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "computed";
    }
}
