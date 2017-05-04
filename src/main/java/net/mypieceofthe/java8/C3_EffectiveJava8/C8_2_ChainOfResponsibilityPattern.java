package net.mypieceofthe.java8.C3_EffectiveJava8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by kgolebiowski on 04/05/2017.
 */
public class C8_2_ChainOfResponsibilityPattern {

    private static final String TEST_STRING = "ADDF xzcv sAFSD ad sASDF ";

    private List<Function<String, String>> listOfChainedOperations;

    private Function<String, String> firstFunction;

    public static void main(String[] args) {
        new C8_2_ChainOfResponsibilityPattern()
                /* This doesn't properly chain the functions, why? and without it the whole pattern
                   cannot be done this way as it does not support dynamic management of listeners */
                .chainOperationsSequentially()
                .executeAndPrint()
                .chainInAFunctionalWay()
                .executeAndPrint();
    }

    private C8_2_ChainOfResponsibilityPattern() {
        listOfChainedOperations = Arrays.asList(
                String::toLowerCase,
                (String s) -> s.replace('a', 'z'),
                String::trim,
                (String s) -> s.substring(0, s.length() / 2)
        );
    }

    private C8_2_ChainOfResponsibilityPattern chainOperationsSequentially() {
        for (int i = 0; i < listOfChainedOperations.size() - 1; i++) {
            listOfChainedOperations.get(i).andThen(listOfChainedOperations.get(i + 1));
        }
        firstFunction = listOfChainedOperations.get(0);
        return this;
    }

    private C8_2_ChainOfResponsibilityPattern chainInAFunctionalWay() {
        firstFunction = listOfChainedOperations.get(0)
                .andThen(listOfChainedOperations.get(1))
                .andThen(listOfChainedOperations.get(2))
                .andThen(listOfChainedOperations.get(3));
        return this;
    }

    private C8_2_ChainOfResponsibilityPattern executeAndPrint() {
        System.out.println(firstFunction.apply(TEST_STRING));
        return this;
    }
}
