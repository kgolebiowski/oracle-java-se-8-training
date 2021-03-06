package net.mypieceofthe.java8.java8inaction.C2_streams.Practice5_5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toSet;

/**
 * Created by kgolebiowski on 29/04/2017.
 */
public class C5_5_Practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);

        System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high)");

        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println("\n2. What are all the unique cities where the traders work?");

        traders.stream()
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet())
                .forEach(System.out::println);

        System.out.println("\n3. Find all traders from Cambridge and sort them by name");

        traders.stream()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("\n4. Return a string of all traders’ names sorted alphabetically");

        System.out.println(
                traders.stream()
                        .map(Trader::getName)
                        .sorted()
                        //.reduce((t1, t2) -> t1 + " " + t2)
                        //.ifPresent(System.out::println);
                        .collect(Collectors.joining(" "))
        );

        System.out.println("\n5. Are any traders based in Milan?");

        traders.stream()
                .filter(trader -> trader.getCity().equals("Milan"))
                .findAny()
                .ifPresent(System.out::println);

        System.out.println(
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"))
        );

        System.out.println("\n6. Print all transactions’ values from the traders living in Cambridge");

        traders.stream()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .flatMap(trader ->
                        transactions.stream()
                                .filter(transaction -> trader.equals(transaction.getTrader())))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println();

        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("\n7. What’s the highest value of all the transactions?");

        transactions.stream()
                .max(comparing(Transaction::getValue))
                .map(Transaction::getValue)
                .ifPresent(System.out::println);


        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        System.out.println("\n8. Find the transaction with the smallest value");

        transactions.stream()
                .min(comparing(Transaction::getValue))
                .ifPresent(System.out::println);

    }
}
