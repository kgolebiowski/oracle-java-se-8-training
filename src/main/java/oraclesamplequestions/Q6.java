package oraclesamplequestions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 08/05/2017.
 */
public class Q6 {

    public static final String SEARCH_DIR = "./src/main/resources/report";

    public static void main(String[] args) {
        try (Stream<Path> st1 = Files.find(Paths.get(SEARCH_DIR), 2,
                (p, a) -> p.toString().endsWith("txt"));
             Stream<Path> st2 = Files.walk(Paths.get(SEARCH_DIR), 2)) {

            st1.forEach(s -> System.out.println("Found: " + s));

            st2.filter(s -> s.toString()
                    .endsWith("txt"))
                    .forEach(s -> System.out.println("Walked: " + s));

        } catch (IOException ioe) {
            System.out.println("Exception");
            ioe.printStackTrace();
        }
    }
}
