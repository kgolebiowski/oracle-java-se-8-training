package net.mypieceofthe.java8.enthuware;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgolebiowski on 13/05/2017.
 */
interface IFoo<F> {
    F getFoo();
}

public class Q1923 {

    static List<String> foos = new ArrayList<>();

    public static <T> T getFoo() {
        return null;
    }

    public static void processIFoo(IFoo<String> ifs) {
        foos.add(ifs.getFoo());
    }

    public static void main(String[] args) {
        foos.<String>add(getFoo());
        foos.add(getFoo()); // Type inference from List

        processIFoo(() -> (String)null);
        processIFoo(() -> (String)null); // Type inference from method parameter processIFoo(IFoo<String> ifs)

        IFoo iFoo = () -> null;
        processIFoo(iFoo); // Same as above
    }
}
