package net.mypieceofthe.java8.C2_streams;

import net.mypieceofthe.java8.domain.dishes.Dish;
import net.mypieceofthe.java8.domain.dishes.DishDomainUsing;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 30/04/2017.
 */
public class C6_2_CollectingAndSummarizing extends DishDomainUsing {

    public static void main(String[] args) {

        // -- Numeric collectors

        assertEquals(
                getDishList().stream()
                        .count(),
                getDishList().stream()
                        .collect(counting()).longValue());

        assertEquals(
                getDishList().stream()
                        .collect(maxBy(comparing(Dish::getCalories))),
                getDishList().stream()
                        .max(comparing(Dish::getCalories)));

        assertEquals(
                getDishList().stream()
                        .mapToInt(Dish::getCalories)
                        .sum(),
                getDishList().stream()
                        .collect(summingInt(Dish::getCalories)).longValue());

        System.out.println("Calories average: " +
                getDishList().stream()
                        .collect(averagingInt(Dish::getCalories))
        );

        // Counts everything in a single operation
        System.out.println(
                getDishList().stream().collect(summarizingInt(Dish::getCalories))
        );

        // -- String collectors

        System.out.println(
                getDishList().stream()
                        .map(Dish::getName)
                        .collect(joining(", "))
        );

        // -- Reducing

        assertEquals(
                getDishList().stream()
                        .mapToInt(Dish::getCalories).sum(),
                getDishList().stream()
                        .collect(reducing(0, Dish::getCalories, (i, j) -> i + j)).intValue()
        );

        assertEquals(
                getDishList().stream()
                        .map(Dish::getCalories).reduce(Integer::sum).get(),
                getDishList().stream()
                        .collect(reducing(0, Dish::getCalories, Integer::sum))

        );

        String shortMenu = getDishList().stream().map(Dish::getName)
                .collect(reducing((s1, s2) -> s1 + s2)).get();
        String shortMenu2 = getDishList().stream()
                .collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
    }
}
