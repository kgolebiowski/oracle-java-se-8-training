package net.mypieceofthe.java8.C3_EffectiveJava8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

import static java.util.stream.Collectors.joining;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class C12_3_TimeZones {
    public static void main(String[] args) {
        new C12_3_TimeZones()
                .basicTimeZones()
                .timeZoneConversions();
    }

    private C12_3_TimeZones basicTimeZones() {
        System.out.println("There are " + ZoneId.getAvailableZoneIds().size() + " available Zone IDs.");
        System.out.println(ZoneId.getAvailableZoneIds().stream()
                .filter(s -> s.startsWith("America"))
                .collect(joining(", ")));

        ZoneId warsawZone = ZoneId.of("Europe/Warsaw");

        LocalDateTime now = LocalDateTime.now();

        System.out.println("Local Zoned time " + now.atZone(warsawZone));

        ZoneOffset currentOffset = warsawZone.getRules().getOffset(now);

        System.out.println("Local time with offset " + now.atOffset(currentOffset));

        return this;
    }

    private C12_3_TimeZones timeZoneConversions() {

        System.out.println();

        ZonedDateTime warsawNow = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
        ZonedDateTime newYorkNow = ZonedDateTime.now(ZoneId.of("America/New_York"));

        System.out.printf("Current time in Warsaw is '%s', while in New York '%s'\n",
                warsawNow.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)),
                newYorkNow.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)));

        ZonedDateTime newYorkLocalTime = ZonedDateTime.of(warsawNow.toLocalDateTime(), ZoneId.of("America/New_York"));

        System.out.printf("Difference between '%s' in WAW and '%s' in NY is %s hours\n",
                warsawNow.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
                newYorkLocalTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
                warsawNow.until(newYorkLocalTime, ChronoUnit.HOURS));

        return this;
    }
}
