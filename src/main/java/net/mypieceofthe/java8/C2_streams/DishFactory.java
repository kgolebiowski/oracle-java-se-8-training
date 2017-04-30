package net.mypieceofthe.java8.C2_streams;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kgolebiowski on 28/04/2017.
 */
public class DishFactory {
    private List<Dish> dishes;

    List<Dish> getDishes() {
        if (this.dishes == null)
            dishes = Arrays.asList(
                    new Dish("pork", false, 800, Dish.Type.MEAT),
                    new Dish("beef", false, 700, Dish.Type.MEAT),
                    new Dish("chicken", false, 400, Dish.Type.MEAT),
                    new Dish("french fries", true, 530, Dish.Type.OTHER),
                    new Dish("rice", true, 350, Dish.Type.OTHER),
                    new Dish("season fruit", true, 120, Dish.Type.OTHER),
                    new Dish("pizza", true, 550, Dish.Type.OTHER),
                    //new Dish("pizza", true, 550, Dish.Type.OTHER),
                    new Dish("prawns", false, 300, Dish.Type.FISH),
                    new Dish("salmon", false, 450, Dish.Type.FISH));

        return dishes;
    }
}
