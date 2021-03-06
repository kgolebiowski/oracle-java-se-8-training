package net.mypieceofthe.java8.java8inaction.C2_streams;

import net.mypieceofthe.java8.java8inaction.domain.dishes.Dish;
import net.mypieceofthe.java8.java8inaction.domain.dishes.DishDomainUsing;

import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class C5_6_NumericStreams extends DishDomainUsing {

    public static void main(String[] args) {
        System.out.println(
                getDishList().stream()
                        .mapToInt(Dish::getCalories)
                        .sum()
        );

        OptionalInt maxCalories = getDishList().stream()
                .mapToInt(Dish::getCalories)
                .max();

        maxCalories.ifPresent(System.out::println);

        // -- Ranges

        System.out.println(
                IntStream.range(1, 10).mapToObj(Integer::toString).collect(Collectors.joining(",")));
        System.out.println(
                IntStream.rangeClosed(1, 10).mapToObj(Integer::toString).collect(Collectors.joining(",")));

        IntStream evenNumbers = IntStream.rangeClosed(0, 100)
                .filter(n -> n % 2 == 0);

        System.out.println(evenNumbers.count());

        // -- Pitagorean triples

        System.out.println();

        int max = 100;

        // Don't really understand what is with about boxing/unboxing here
        Stream<int[]> pitTriplesFirstWay = IntStream.rangeClosed(1, max).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, max)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        Stream<double[]> pitTriplesSecondWay = IntStream.rangeClosed(1, max).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, max)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(res -> res[2] % 1 == 0)
                );

        pitTriplesSecondWay.forEach(res -> System.out.printf("%.0f, %.0f, %.0f\n", res[0], res[1], res[2]));
    }
}
