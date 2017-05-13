package net.mypieceofthe.java8.enthuware;

import net.mypieceofthe.java8.java8inaction.domain.dishes.Dish;

import java.util.List;

/**
 * Created by kgolebiowski on 13/05/2017.
 */
public class Q1789 {
    public List<Dish> getDishList() {
        return null;
    }

    public List<String> getStringList() {
        return null;
    }

    public void printNames() { }

    public void printNamesWithParam(String str) { }

    public String printNamesReturningString() {
        return null;
    }

    public static void main(String[] args) {
        Q1789 n = new Q1789();

        n.getStringList().forEach(s -> n.printNames());

        n.getStringList().forEach(n::printNamesWithParam);

        n.getStringList().forEach(s -> n.printNamesReturningString());

        n.getDishList().forEach(Dish::getName);
    }
}