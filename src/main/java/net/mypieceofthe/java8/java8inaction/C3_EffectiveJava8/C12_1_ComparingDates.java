package net.mypieceofthe.java8.java8inaction.C3_EffectiveJava8;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 13/05/2017.
 */
public class C12_1_ComparingDates {

    public static void main(String[] args) {
        System.out.println(
                Duration.between(
                        LocalDateTime.now(),
                        LocalDateTime.of(LocalDate.now(), LocalTime.of(15,30))));

        assertEquals(Duration.ofMinutes(3), Duration.of(3, ChronoUnit.MINUTES));

        System.out.println(
                Period.between(
                        LocalDate.now(),
                        LocalDate.of(1987, 1, 21)));

        assertEquals(Period.ofDays(3), Period.of(0, 0, 3));

        System.out.println();

        // Negative result
        System.out.println(Period.between(
                LocalDate.of(2015, Month.SEPTEMBER, 2), // Later
                LocalDate.of(2015, Month.SEPTEMBER, 1))); // Sooner

        System.out.println();

        // OCP Study Guide: Chapter 5 Dates, Strings, Localization; Review question 15
        String m1 = Duration.of(1, ChronoUnit.MINUTES).toString();
        String m2 = Duration.ofMinutes(1).toString();
        String s = Duration.of(60, ChronoUnit.SECONDS).toString();
        String d = Duration.ofDays(1).toString();
        String p = Period.ofDays(1).toString();

        Stream.of(m1, m2, s, d, p)
                .forEach(s1 -> System.out.println(s1 + " [" + s1.hashCode() + "]"));

        Stream.of(m1 == m2, m1.equals(m2), m1.equals(s), d == p, d.equals(p))
                .forEach(System.out::println);
    }
}
