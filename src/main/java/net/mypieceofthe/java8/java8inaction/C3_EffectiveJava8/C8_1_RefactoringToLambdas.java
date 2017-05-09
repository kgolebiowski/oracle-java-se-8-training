package net.mypieceofthe.java8.java8inaction.C3_EffectiveJava8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C8_1_RefactoringToLambdas {

    private static final Logger _log = LogManager.getLogger(C8_1_RefactoringToLambdas.class);

    int instanceVar = 5;

    public static void main(String[] args) {
        new C8_1_RefactoringToLambdas()
                .basicLambdas()
                .thisAndVariablesScopeInLambdas()
                .lambdaClashesFromOverloading();
    }

    private C8_1_RefactoringToLambdas basicLambdas() {
        _log.error(Thread::activeCount); // Deferred Thread.activeCount() method execution
        return this;
    }

    private C8_1_RefactoringToLambdas thisAndVariablesScopeInLambdas() {
        int a = 10;

        Runnable r1 = () -> {
            //int a = 2; // Error:(13, 17) java: variable a is already defined in the scope
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

        return this;
    }

    private C8_1_RefactoringToLambdas lambdaClashesFromOverloading() {
        //doSomething(() -> System.out.println("asdf")); // Ambiguous method call, requires explicit casting
        doSomething((TheInterfaceThatLooksTheSameAsRunnable) () -> System.out.println("Message"));
        return this;
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