package net.mypieceofthe.java8.C2_streams;

import net.mypieceofthe.java8.domain.dishes.Dish;
import net.mypieceofthe.java8.domain.dishes.DishDomainUsing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class C5_3_SearchingStreams extends DishDomainUsing {

    public static void main(String[] args) {
        if(getDishList().stream().allMatch(dish -> dish.getCalories() < 1200))
            System.out.println("All less than x!");

        getDishList().stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9

        firstSquareDivisibleByThree.ifPresent(System.out::println);
    }
}
