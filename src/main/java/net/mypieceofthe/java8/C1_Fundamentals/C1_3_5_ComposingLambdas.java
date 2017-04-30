package net.mypieceofthe.java8.C1_Fundamentals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by kgolebiowski on 26/04/2017.
 */
public class C1_3_5_ComposingLambdas {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("aaa", "b", "b", "ccc", "DD");

        stringList.sort(
                Comparator.comparing(String::length)
                        .reversed()
                        .thenComparing(String::hashCode));

        stringList.forEach(System.out::println);

        System.out.println();

        stringList.forEach((s -> System.out.println(s + " [" + s.hashCode() + "]")));

        System.out.println();

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);

        System.out.println(h.apply(1));

        System.out.println();

        f = x -> x + 1;
        g = x -> x * 2;
        h = f.compose(g);

        System.out.println(h.apply(1));
    }
}


