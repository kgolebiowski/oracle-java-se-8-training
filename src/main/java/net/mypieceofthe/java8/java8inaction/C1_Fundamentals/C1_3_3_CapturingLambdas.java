package net.mypieceofthe.java8.java8inaction.C1_Fundamentals;

/**
 * Created by kgolebiowski on 26/04/2017.
 */
public class C1_3_3_CapturingLambdas {
    int someInstanceVar = 10;

    public static void main(String[] args) { }

    void doSome() {
        int otherLocalVar = 5;
        final int otherFinalLocalVar = 6;

        // otherLocalVar = 2; // second lambda won't compile with "Variable should be effectively final"

        Runnable lambda = () -> System.out.println(someInstanceVar);

        lambda = () -> System.out.println(otherLocalVar);

        lambda = () -> System.out.println(otherFinalLocalVar);

        // otherLocalVar = 2; // second lambda won't compile with "Variable should be effectively final"

        // Lambda as anonymous class - same rules apply
        lambda = new Runnable() {
            @Override
            public void run() {
                System.out.println(otherLocalVar);
            }
        };
    }
}