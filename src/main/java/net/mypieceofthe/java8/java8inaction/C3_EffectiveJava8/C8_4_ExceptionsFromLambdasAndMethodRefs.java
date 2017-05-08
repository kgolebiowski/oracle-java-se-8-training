package net.mypieceofthe.java8.java8inaction.C3_EffectiveJava8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C8_4_ExceptionsFromLambdasAndMethodRefs {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("asdf", null);

        try {
            strings.stream().map(s -> s.length()).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            strings.stream().map(String::length).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Stream.of(1, 2, 3).map(C8_4_ExceptionsFromLambdasAndMethodRefs::divideByZero).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int divideByZero(int n) {
        return n / 0;
    }
}