package net.mypieceofthe.java8.java8inaction.C2_streams;

import net.mypieceofthe.java8.java8inaction.domain.dishes.Dish;
import net.mypieceofthe.java8.java8inaction.domain.dishes.DishDomainUsing;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class C5_4_Reduce extends DishDomainUsing {
    public static void main(String[] args) {
        // -- counting

        getDishList().stream()
                .reduce((d1, d2) ->
                        new Dish("", false, d1.getCalories() + d2.getCalories(), Dish.Type.OTHER))
                .ifPresent(dish -> System.out.println(dish.getCalories()));

        getDishList().stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

        // -- max/min

        getDishList().stream()
                .map(Dish::getCalories)
                .reduce(Integer::max)
                .ifPresent(c -> System.out.println("Maximum calory dish has " + c));

        getDishList().stream()
                .map(Dish::getCalories)
                .reduce(Integer::min)
                .ifPresent(c -> System.out.println("Minimum calory dish has " + c));
    }
}
