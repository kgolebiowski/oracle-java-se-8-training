package net.mypieceofthe.java8.C2_streams;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class C5_6_NumericStreams {

    public static void main(String[] args) {
        DishFactory factory = new DishFactory();

        System.out.println(
                factory.getDishes().stream()
                        .mapToInt(Dish::getCalories)
                        .sum()
        );

        OptionalInt maxCalories = factory.getDishes().stream()
                .mapToInt(Dish::getCalories)
                .max();

        maxCalories.ifPresent(System.out::println);

        // -- Ranges

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
