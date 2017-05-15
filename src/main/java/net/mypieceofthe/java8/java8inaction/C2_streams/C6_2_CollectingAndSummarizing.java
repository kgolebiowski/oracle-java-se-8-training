package net.mypieceofthe.java8.java8inaction.C2_streams;

import net.mypieceofthe.java8.java8inaction.domain.dishes.Dish;
import net.mypieceofthe.java8.java8inaction.domain.dishes.DishDomainUsing;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 30/04/2017.
 */
public class C6_2_CollectingAndSummarizing extends DishDomainUsing {

    public static void main(String[] args) {
        new C6_2_CollectingAndSummarizing()
                .numericCollectors()
                .stringCollectors()
                .toMapCollector()
                .reducing()
                .forEachAndOrdered();

    }

    private C6_2_CollectingAndSummarizing numericCollectors() {
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
                        .collect(averagingInt(Dish::getCalories)));

        // Counts everything in a single operation
        System.out.println(
                getDishList().stream().collect(summarizingInt(Dish::getCalories))
        );

        return this;
    }

    private C6_2_CollectingAndSummarizing stringCollectors() {
        System.out.println(
                getDishList().stream()
                        .map(Dish::getName)
                        .collect(joining(", "))
        );

        // OCP Study Guide: Chapter 4 Functional Programming, review question 11
        // Does not compile as Collectors.joining() expect the String (see next)
        //Stream.iterate(1, x -> x = x + 1)
        //        .limit(5)
        //        .collect(Collectors.joining());

        System.out.println(
                Stream.iterate(1, x -> x = x + 1)
                        .limit(5)
                        .map(Object::toString)
                        .collect(Collectors.joining()));

        return this;
    }

    private C6_2_CollectingAndSummarizing toMapCollector() {

        System.out.println("Collected map: " + getDishList().stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getType)));

        return this;
    }

    private C6_2_CollectingAndSummarizing reducing() {
        assertEquals(
                getDishList().stream()
                        .mapToInt(Dish::getCalories).sum(),
                getDishList().stream()
                        .collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j)).intValue()
        );

        assertEquals(
                getDishList().stream()
                        .map(Dish::getCalories).reduce(Integer::sum).get(),
                getDishList().stream()
                        .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum))

        );

        String shortMenu = getDishList().stream().map(Dish::getName)
                .collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
        String shortMenu2 = getDishList().stream()
                .collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));

        return this;
    }

    private C6_2_CollectingAndSummarizing forEachAndOrdered() {
        System.out.println();

        Integer[] arrayOfNumbers = {9, 8, 1, 5, 2, 3, 4, 7};

        System.out.println("forEach");
        Arrays.stream(arrayOfNumbers).forEach(System.out::print);

        System.out.println("\n\nforEach parallel (different result every execution)");
        Arrays.stream(arrayOfNumbers).parallel().forEach(System.out::print);

        System.out.println("\n\nforEachOrdered parallel (same result every execution)");
        Arrays.stream(arrayOfNumbers).parallel().forEachOrdered(System.out::print);

        System.out.println("\n\nsorted");
        Arrays.stream(arrayOfNumbers).sorted().forEach(System.out::print);

        return this;
    }
}
