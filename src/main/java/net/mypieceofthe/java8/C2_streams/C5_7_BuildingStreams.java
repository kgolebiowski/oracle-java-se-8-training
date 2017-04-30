package net.mypieceofthe.java8.C2_streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class C5_7_BuildingStreams {
    public static void main(String[] args) {
        Stream<String> emptyStream = Stream.empty();

        System.out.println(emptyStream.findAny().isPresent());

        Stream.of("Hello", "World").map(String::toUpperCase).forEach(System.out::println);

        System.out.println();

        // -- Streams from files

        try (Stream<String> lines = Files.lines(Paths.get("/Users/kgolebiowski/Desktop/source_code.txt"))) {
            lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .filter(line -> line.contains("Exception"))
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        // -- Streams from functions - iterate

        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        System.out.println();

        // -- Fibonacci tuples series

        Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                .limit(20)
                .forEach((int[] res) -> System.out.printf("[%d, %d]\n", res[0], res[1]));

        System.out.println();

        // -- Streams from functions - generate

        DoubleStream.generate(Math::random)
                .mapToInt(r -> (int) (r * 10000))
                .limit(10)
                .forEach(System.out::println);
    }
}
