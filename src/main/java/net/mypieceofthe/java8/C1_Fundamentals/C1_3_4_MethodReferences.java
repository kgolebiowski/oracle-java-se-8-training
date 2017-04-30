package net.mypieceofthe.java8.C1_Fundamentals;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

/**
 * Created by kgolebiowski on 26/04/2017.
 */
public class C1_3_4_MethodReferences {

    public static void main(String[] args) {

        Runnable runnableLambda = () -> System.out.println();
        runnableLambda.run();

        Runnable runnableEx = () -> System.exit(0);

        Runnable runnableReference = System.out::println;
        runnableReference.run();

        Consumer<String> cons = System.out::println;
        cons.accept("Accepted by consumer");

        Function<String, Integer> strToIntLambda = (String s) -> Integer.parseInt(s);
        Function<String, Integer> strToIntRef = Integer::parseInt;

        BiPredicate<List<String>, String> containsLambda = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> containsRef = List::contains;

        List<String> str = Arrays.asList("aaa","b","AA","B");

        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(str);

        str.sort(String::compareToIgnoreCase);
        System.out.println(str);

        str.sort(comparing(String::length));
        System.out.println(str);

        System.out.println("Define AppleNoArg");
        Supplier<AppleNoArg> c1ref = AppleNoArg::new;
        System.out.println("Apply AppleNoArg");
        AppleNoArg a1 = c1ref.get();

        Supplier<AppleNoArg> c1lamb = () -> new AppleNoArg();
        AppleNoArg a2 = c1lamb.get();

        System.out.println("Define AppleSingleArg");
        Function<Integer, AppleSingleArg> c2ref = AppleSingleArg::new;
        System.out.println("Apply AppleSingleArg");
        AppleSingleArg a3 = c2ref.apply(110);

        Function<Integer, AppleSingleArg> c2lamb = (weight) -> new AppleSingleArg(weight);
        AppleSingleArg a4 = c2lamb.apply(110);

        class Doer {
            private void doSomething() {
                System.out.println("Method referenced instance object");
            }
        }

        instanceMethodReference(new Doer()::doSomething);
    }

    private static void instanceMethodReference(Runnable runnable) {
         runnable.run();
    }
}

class AppleNoArg {
    AppleNoArg() {
        System.out.println("Executed AppleNoArg");
    }
}

class AppleSingleArg {
    AppleSingleArg(Integer param) {
        System.out.println("Executed AppleSingleArg");
    }
}
