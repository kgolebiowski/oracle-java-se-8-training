package net.mypieceofthe.java8.oraclesamplequestions;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kgolebiowski on 08/05/2017.
 */
public class Q3 {
    public static void main(String[] args) {
        List<String> courses = Arrays.asList("Java", "Oracle", "JSF", "EJB");
        //int count = courses.stream().filter(s -> s.startsWith("J")).count(); // count returns long !
        long count = courses.stream().filter(s -> s.startsWith("J")).count();
        System.out.println(count);

        long assignToLong =  returnsInt(); // int can be implicitly casted to long (no data truncation)
        //int assignToInt = returnsLong();

    }

    private static int returnsInt() {
        return 0;
    }

    private static long returnsLong() {
        return 0;
    }
}
