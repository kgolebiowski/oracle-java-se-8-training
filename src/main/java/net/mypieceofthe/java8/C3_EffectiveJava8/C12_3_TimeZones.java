package net.mypieceofthe.java8.C3_EffectiveJava8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static java.util.stream.Collectors.joining;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class C12_3_TimeZones {
    public static void main(String[] args) {
        new C12_3_TimeZones()
                .basicTimeZones();
    }

    private C12_3_TimeZones basicTimeZones() {
        System.out.println("There are " + ZoneId.getAvailableZoneIds().size() + " time zones.");
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
}
