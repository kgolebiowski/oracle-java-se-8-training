package net.mypieceofthe.java8.exam;

/**
 * Created by kgolebiowski on 16/05/2017.
 */


public interface InterfaceWithDefaultMethodCallingAbstract {
    void doSomething();

    default void defaultBehaviour() {
        doSomething(); // Does not produce compile error !
    }
}
