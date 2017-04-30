package net.mypieceofthe.java8.C2_streams;

import net.mypieceofthe.java8.domain.dishes.Dish;
import net.mypieceofthe.java8.domain.dishes.DishDomainUsing;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Created by kgolebiowski on 30/04/2017.
 */
public class C6_4_Partitioning extends DishDomainUsing {
    public static void main(String[] args) {
        Map<Boolean, List<Dish>> byVegetarian = getDishList().stream()
                .collect(partitioningBy(Dish::isVegetarian));

        System.out.println("Vegetarian dishes are: " + byVegetarian.get(true));

        System.out.println(
                getDishList().stream()
                        .collect(
                                partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))
                        )
        );

        System.out.println(
                getDishList().stream()
                        .collect(
                                partitioningBy(Dish::isVegetarian,
                                        collectingAndThen(
                                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                                Optional::get
                                        )))
        );

        // -- Prime/Non prime number streams

        IntPredicate isPrime =
                candidate -> IntStream.range(2, candidate).noneMatch(j -> candidate % j == 0);

        Map<Boolean, List<Integer>> numbers =
                IntStream.range(0, 150).boxed().collect(partitioningBy(isPrime::test));

        System.out.printf("Prime numbers: %s (%s)\n",
                numbers.get(true).stream().map(Object::toString).collect(joining(",")),
                numbers.get(true).stream().collect(summarizingInt(Integer::intValue))
        );

        System.out.println("Other numbers: " + numbers.get(false).stream()
                .map(Object::toString).collect(joining(","))
        );
    }
}