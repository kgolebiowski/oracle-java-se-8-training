package net.mypieceofthe.java8.java8inaction.C3_EffectiveJava8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static org.junit.Assert.assertEquals;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class C12_2_ManipulatingDates {
    public static void main(String[] args) {
        new C12_2_ManipulatingDates()
                .basicOperations()
                .temporalAdjusters()
                .quiz12_2();
    }

    private C12_2_ManipulatingDates basicOperations() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        System.out.println(date);
        return this;
    }

    private C12_2_ManipulatingDates temporalAdjusters() {
        LocalDate date = LocalDate.of(2017, 5, 6);
        System.out.printf("Initial date '%s', firstDayOfMonth '%s', next friday '%s'\n",
                date,
                date.with(firstDayOfMonth()),
                date.with(nextOrSame(DayOfWeek.FRIDAY)));
        return this;
    }

    private C12_2_ManipulatingDates quiz12_2() {
        LocalDate wednesday = LocalDate.of(2017, 5, 3);
        LocalDate friday = LocalDate.of(2017, 5, 5);
        LocalDate saturday = LocalDate.of(2017, 5, 6);

        assertEquals(LocalDate.of(2017, 5, 4), wednesday.with(new NextWorkingDay()));
        assertEquals(LocalDate.of(2017, 5, 8), friday.with(new NextWorkingDay()));
        assertEquals(LocalDate.of(2017, 5, 8), saturday.with(new NextWorkingDay()));

        Stream.of(wednesday, friday, saturday).forEach(d ->
                System.out.printf("Next working day after %s is %s\n",
                        d.getDayOfWeek(), d.with(new NextWorkingDay()).getDayOfWeek()));

        return this;
    }
}

class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal initial) {
        Temporal result = initial;
        do {
            result = result.plus(1, ChronoUnit.DAYS);
        } while (result.get(ChronoField.DAY_OF_WEEK) > DayOfWeek.FRIDAY.getValue());
        return result;
    }
}