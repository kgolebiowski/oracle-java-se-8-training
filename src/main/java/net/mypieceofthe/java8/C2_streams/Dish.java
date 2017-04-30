package net.mypieceofthe.java8.C2_streams;

import java.util.Objects;

/**
 * Created by kgolebiowski on 28/04/2017.
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dish) {
            System.out.println("Equals: " + this.getName().equals(((Dish) obj).getName()));
            return this.getName().equals(((Dish) obj).getName());
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vegetarian, calories, type);
    }

    public enum Type {MEAT, FISH, OTHER}
}
