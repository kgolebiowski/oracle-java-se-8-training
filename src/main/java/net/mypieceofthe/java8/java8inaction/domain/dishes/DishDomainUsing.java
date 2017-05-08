package net.mypieceofthe.java8.java8inaction.domain.dishes;

import java.util.List;

/**
 * Created by kgolebiowski on 30/04/2017.
 */
public abstract class DishDomainUsing {

    private static DishFactory dishFactory;

    static {
        dishFactory = new DishFactory();
    }

    protected static List<Dish> getDishList() {
        return dishFactory.getDishes();
    }
}
