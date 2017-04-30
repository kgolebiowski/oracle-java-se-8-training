package net.mypieceofthe.java8.C2_streams;

import net.mypieceofthe.java8.domain.dishes.Dish;
import net.mypieceofthe.java8.domain.dishes.DishDomainUsing;

import static java.util.stream.Collectors.reducing;
import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 30/04/2017.
 */
public class C6_3_ReducingAndSummarizing extends DishDomainUsing {
    public static void main(String[] args) {
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
