package net.mypieceofthe.java8.C1_Fundamentals;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by kgolebiowski on 25/04/2017.
 */
public class C1_3_1_Lambdas {
    public static void main(String[] args) {

        /* Some lambdas for generic interfaces from java.util.function */

        Predicate<String> notEmptyPredicate = (String str) -> !str.isEmpty();

        Supplier l1 = () -> "asdf from supplier";

        System.out.println(l1.get());

        Supplier l2 = () -> {
            return "asdf" + 5;
        };

        // Supplier l3 = () -> { "asdf" + 5; }; // { } require 'return' statement

        Function<Integer, String> l4 = (Integer a) -> {
            System.out.println("asdf" + a);
            return "asdf";
        };

        /* Lambdas for other usefull Java (functional) interfaces */

        Comparator<List> listComparator =
                (List o1, List o2) -> new Integer(o1.size()).compareTo(o2.size());

        Comparator<List> listComparatorInferredTypes =
                (o1, o2) -> new Integer(o1.size()).compareTo(o2.size());

        new Thread(() -> System.out.println("siema")).run();

        /* Lambdas for other custom functional interface */

        MyFunctionalInterface mfi = (String a) -> "This comes from lambda " + a;

        System.out.println(mfi.getClass() + " : " + mfi.test("oo"));
    }

    public Callable<String> execute() {
        return () -> "adsf";
    }
}

@FunctionalInterface // Optional
interface MyFunctionalInterface {

    String test(String a);

    boolean equals(Object obj); // ?????

    // String test2(String f); // Fails to compile as FunctionalInterface defines only one abstract method

    default String testDefault() {
        return "";
    }
}