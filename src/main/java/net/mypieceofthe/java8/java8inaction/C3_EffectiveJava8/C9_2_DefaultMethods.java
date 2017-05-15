package net.mypieceofthe.java8.java8inaction.C3_EffectiveJava8;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C9_2_DefaultMethods {
    public static void main(String[] args) {
        new SomeConcreteClass().hello();
    }
}

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}

interface C {
    default void hello() {
        System.out.println("Hello from C");
    }
}

// enthuware 1477
class SomeConcreteClass2 implements A, B {
    void helloFromBIsMoreSpecific() {
        this.hello();
    }
}

class SomeConcreteClass implements B, A, C {
    @Override
    public void hello() {
        //A.super.hello(); // Error:(33, 10) java: bad type qualifier ...
        B.super.hello(); // Must be explicitly chosen
    }
}

// -- Same methods and different types

interface Z {
    default Number getNumber() {
        return 10;
    }
}

interface X {
    default String getNumber() {
        return "42";
    }
}

// Won't compile at all, this combination of interfaces cannot be used

//class V implements Z, X {
//    public static void main(String... args) {
//        System.out.println(new V().getNumber());
//    }
//
//    @Override
//    public String getNumber() {
//        return null;
//    }
//}
