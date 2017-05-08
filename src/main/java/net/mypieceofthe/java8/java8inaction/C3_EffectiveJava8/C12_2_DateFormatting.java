package net.mypieceofthe.java8.java8inaction.C3_EffectiveJava8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class C12_2_DateFormatting {
    public static void main(String[] args) {
        new C12_2_DateFormatting()
                .basicFormattingAndParsing()
                .formattingWithBuilder();
    }

    private C12_2_DateFormatting basicFormattingAndParsing() {
        LocalDate now = LocalDate.now();

        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));

        System.out.println(LocalDate.parse("2017-05-06"));
        System.out.println(LocalDate.parse("2017-05-06", DateTimeFormatter.ISO_LOCAL_DATE));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(now.format(dtf) + " " + LocalDate.parse("06/05/2017", dtf));

        DateTimeFormatter dtfItalian = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        System.out.println(now.format(dtfItalian));

        return this;
    }

    private C12_2_DateFormatting formattingWithBuilder() {
        DateTimeFormatterBuilder genericFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_WEEK)
                .appendLiteral(", ")
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(" ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR);

        Stream.of(Locale.ENGLISH, Locale.ITALIAN, Locale.GERMAN, Locale.JAPANESE)
                .forEach(locale -> System.out.println(LocalDate.now().format(genericFormatter.toFormatter(locale))));

        return this;
    }
}
