package net.mypieceofthe.java8.C2_streams;

import net.mypieceofthe.java8.domain.dishes.Dish;
import net.mypieceofthe.java8.domain.dishes.DishDomainUsing;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by kgolebiowski on 28/04/2017.
 */
public class C4_4_DiscoveringStreams extends DishDomainUsing {
    public static void main(String[] args) {
        // -- Skip and limit

        List<String> names =
                getDishesList().stream()
                        .filter(d -> {
                            System.out.printf("Filtering '%s' (%b)\n", d.getName(), d.getCalories() > 300);
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("Mapping " + d.getName());
                            return d.getName();
                        })
                        .skip(1)
                        .limit(3)
                        .collect(toList());

        System.out.println("\nResults: ");
        names.forEach(System.out::println);

        // -- distinct for objects

        long count = getDishesList().stream()
                .filter(d -> d.getCalories() > 300)
                .distinct() // Uses hashcode and equals
                .count();
        System.out.println("\n" + count + "\n");

        // -- filter and distinct on primitives

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 5);

        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        // -- twice map

        System.out.println(
                getDishesList().stream()
                        .map(Dish::getName)
                        .map(String::length)
                        .collect(toList())
        );

        System.out.println();

        // -- flatMap

        System.out.println(
                Arrays.stream(new String[]{"Hello", "World"})
                        .map(word -> word.split(""))
                        //.map(a -> Arrays.stream(a))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList())
        );

        System.out.println();

        // --

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);

        System.out.println(
                list1.stream()
                        .flatMap(i -> list2.stream()
                                .filter(j -> (j + i) % 3 == 0) // filter out only divisible by three
                                .map(j -> Arrays.asList(i, j))
                        )
                        .collect(toList())
        );
    }
}
