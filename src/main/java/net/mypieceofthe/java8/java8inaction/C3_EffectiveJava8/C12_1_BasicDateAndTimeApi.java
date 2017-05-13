package net.mypieceofthe.java8.java8inaction.C3_EffectiveJava8;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class C12_1_BasicDateAndTimeApi {
    public static void main(String[] args) {
        new C12_1_BasicDateAndTimeApi()
                .datesAndTimes()
                .instants();
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

        System.out.println();

        return this;
    }

    private C12_1_BasicDateAndTimeApi instants() {
        System.out.println(String.join(", ", Instant.EPOCH.toString(), Instant.now().toString()));

        System.out.println(ZonedDateTime.now().toInstant());
        //System.out.println(LocalDateTime.now().toInstant(); // Requires Offset

        ZoneOffset currentOffset = ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now());
        System.out.println(currentOffset);

        System.out.println(Instant.now().toEpochMilli()); // Returns seconds (standard timestamp) not miliseconds!

        System.out.println();

        return this;
    }
}
