package net.mypieceofthe.java8.C3_EffectiveJava8;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class C12_1_BasicDateAndTimeApi {
    public static void main(String[] args) {
        new C12_1_BasicDateAndTimeApi()
                .datesAndTimes()
                .instants()
                .durationsAndPeriods();
    }

    private C12_1_BasicDateAndTimeApi datesAndTimes() {
        LocalDate localDate = LocalDate.now();

        System.out.printf("Today is %s, %d.%d.%d\n",
                localDate.getDayOfWeek(),
                localDate.getDayOfMonth(),
                localDate.getMonthValue(),
                localDate.getYear());

        System.out.printf("Today is %s, %d.%d.%d\n",
                localDate.get(ChronoField.DAY_OF_WEEK),
                localDate.get(ChronoField.DAY_OF_MONTH),
                localDate.get(ChronoField.MONTH_OF_YEAR),
                localDate.get(ChronoField.YEAR));

        LocalTime localTime = LocalTime.parse("10:30:24");

        assertEquals(
                localTime, LocalTime.of(10, 30, 24));

        System.out.println(localTime);

        try {
            LocalTime.parse("10:3ss0:24");
        } catch (DateTimeParseException ignored) {
        }

        System.out.printf("Now '%s', local date at time '%s' and local time at date '%s'. " +
                        "Now datetime without date part '%s'\n",
                LocalDateTime.now(),
                localDate.atTime(localTime),
                localTime.atDate(localDate),
                LocalDateTime.now().toLocalTime());

        return this;
    }

    private C12_1_BasicDateAndTimeApi instants() {
        System.out.println(String.join(", ", Instant.EPOCH.toString(), Instant.now().toString()));
        return this;
    }

    private C12_1_BasicDateAndTimeApi durationsAndPeriods() {
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

        return this;
    }
}
