package net.mypieceofthe.java8.java8inaction.C2_streams;

import net.mypieceofthe.java8.java8inaction.domain.dishes.Dish;
import net.mypieceofthe.java8.java8inaction.domain.dishes.DishDomainUsing;

import java.util.Optional;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class C5_4_Reduce extends DishDomainUsing {
    public static void main(String[] args) {
        new C5_4_Reduce()
                .summing()
                .maxAndMin();
    }

    private C5_4_Reduce summing() {
        Optional<Dish> sumWithoutMapping = getDishList().stream()
                .reduce((d1, d2) ->
                        new Dish("", false, d1.getCalories() + d2.getCalories(), Dish.Type.OTHER));

        sumWithoutMapping.ifPresent(dish -> System.out.println(dish.getCalories()));

        Optional<Integer> sumWithMapReduce = getDishList().stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum);

        sumWithMapReduce.ifPresent(System.out::println);

        Integer sumWithReduceOnly = getDishList().stream()
                .reduce(0, (sum, dish) -> sum + dish.getCalories(), (s1, s2) -> s1 + s2);

        System.out.println(sumWithReduceOnly);

        return this;
    }

    private C5_4_Reduce maxAndMin() {
        getDishList().stream()
                .map(Dish::getCalories)
                .reduce(Integer::max)
                .ifPresent(c -> System.out.println("Maximum calory dish has " + c));

        getDishList().stream()
                .map(Dish::getCalories)
                .reduce(Integer::min)
                .ifPresent(c -> System.out.println("Minimum calory dish has " + c));

        return this;
    }
}
