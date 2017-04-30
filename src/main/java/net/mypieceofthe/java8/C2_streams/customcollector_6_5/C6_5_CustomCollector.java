package net.mypieceofthe.java8.C2_streams.customcollector_6_5;

import net.mypieceofthe.java8.domain.dishes.Dish;
import net.mypieceofthe.java8.domain.dishes.DishDomainUsing;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgolebiowski on 30/04/2017.
 */
public class C6_5_CustomCollector extends DishDomainUsing {
    public static void main(String[] args) {
        Assert.assertEquals(
                getDishList().stream()
                        .map(Dish::getName).collect(new ToListCollector<>()),
                getDishList().stream()
                        .map(Dish::getName).collect(ArrayList::new, List::add, List::addAll)
        );
    }
}
