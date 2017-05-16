package net.mypieceofthe.java8.exam;

/**
 * Created by kgolebiowski on 16/05/2017.
 */
interface SomeInterfaceWithCallDefaultMethod {
    default void call() {};
}

interface InterfaceWithStaticCallMethod {
    static void call() {};
}

public class StaticVersusDefaultMethod
        implements SomeInterfaceWithCallDefaultMethod, InterfaceWithStaticCallMethod {

    public void call() {
        SomeInterfaceWithCallDefaultMethod.super.call();
    };

    public static void main(String[] args) {
        StaticVersusDefaultMethod sm = new StaticVersusDefaultMethod();

        sm.call();

        InterfaceWithStaticCallMethod.call();
    }
}
