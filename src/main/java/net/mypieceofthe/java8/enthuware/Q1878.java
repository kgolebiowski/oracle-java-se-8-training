package net.mypieceofthe.java8.enthuware;

import java.util.function.Function;

/**
 * Created by kgolebiowski on 13/05/2017.
 *
 * Why the following gives an error
 *
 *      Double twoIntsToDouble = 2 * 3 // Inconvertible types required Double but found int
 *
 * while this one is completely fine
 *
 *      Double fromIntMethod = methodReturningIntegerAsDouble();
 *
 */

public class Q1878 {
    public static void main(String[] args) {
        double principle = 100;
        int interestrate = 5;
        double amount1 = compute1(principle, x -> x * interestrate);
        double amount2 = compute2(principle, x -> x * interestrate);
        double amount3 = compute2WithAnonClass(principle, null);
        //double amount4 = compute3(principle, x -> x * interestrate); // int cannot be converted to double
        //double amount5 = compute3WithAnonClass(principle, null);

        Double doubleAndInt = 2.0 * 3; // OK
        //Double twoIntsToDouble = 2 * 3; // Inconvertible types required Double but found int
        Double fromIntMethod = methodReturningIntegerAsDouble();
    }

    public static double compute1(double base, Function<Double, Double> func) {
        return func.apply(base);
    }

    public static double compute2(double base, Function<Integer, Integer> func) {
        return func.apply((int) base);
    }

    public static double compute2WithAnonClass(double base, Function<Integer, Integer> func) {
        return new Function<Integer, Integer>() {
            public Integer apply(Integer x) {
                return x * 5;
            }
        }.apply((int) base);
    }

    public static double compute3(double base, Function<Integer, Double> func) {
        return func.apply((int) base);
    }

    /* public static double compute3WithAnonClass(double base, Function<Integer, Double> func) {
        return new Function<Integer, Double>() {
            public Double apply(Integer x) {
                return x * 5; // Inconvertible types required Double but found int
            }
        }.apply((int) base);
    } */

    public static double methodReturningIntegerAsDouble() {
        return 2 * 3;
    }
}