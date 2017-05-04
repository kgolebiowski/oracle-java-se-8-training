package net.mypieceofthe.java8.C3_EffectiveJava8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C8_1_RefactoringToLambdas {

    private static final Logger _log = LogManager.getLogger(C8_1_RefactoringToLambdas.class);

    int instanceVar = 5;

    public static void main(String[] args) {
        new C8_1_RefactoringToLambdas().runAsObject();
    }

    private void runAsObject() {

        _log.error(Thread::activeCount); // Deferred Thread.activeCount() method execution

        thisAndVariablesScopeInLambdas();
        lambdaClashesFromOverloading();
    }

    private void thisAndVariablesScopeInLambdas() {
        int a = 10;

        Runnable r1 = () -> {
            //int a = 2; // Error:(13, 17) java: variable a is already defined in method main(java.lang.String[])
            System.out.println(a);
            System.out.println(this); // Refers to enclosing object
        };

        r1.run();

        Runnable r2 = new Runnable() {
            public void run() {
                int a = 2;
                System.out.println(a);
                System.out.println(this); // Refers to this Runnable instance
            }
        };

        r2.run();
    }

    private void lambdaClashesFromOverloading() {
        //doSomething(() -> System.out.println("asdf")); // Ambiguous method call, requires explicit casting
        doSomething((TheInterfaceThatLooksTheSameAsRunnable) () -> System.out.println("Message"));
    }

    public void doSomething(Runnable r) {
        r.run();
    }

    public void doSomething(TheInterfaceThatLooksTheSameAsRunnable a) {
        a.run();
    }


}

interface TheInterfaceThatLooksTheSameAsRunnable {
    void run();
}