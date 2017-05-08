package net.mypieceofthe.java8.java8inaction.AppB_MiscLibraryUpdates;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class CollectionsUpdates {
    public static void main(String[] args) {
        new CollectionsUpdates()
                .mapUpdates()
                .listUpdates();
    }

    private CollectionsUpdates mapUpdates() {
        Map<String, Integer> carInventory = new HashMap<>();
        carInventory.put("Fiat Panda", 3);
        carInventory.put("Mitsubishi Lancer", 5);

        System.out.println(carInventory.getOrDefault("Aston Martin", 0));

        System.out.println(carInventory.compute("Fiat Panda", (s, i) -> s.length() + i));

        System.out.println(carInventory.computeIfAbsent("Fiat Panda 3", String::length));

        carInventory.entrySet().stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .forEach(System.out::println);

        return this;
    }

    private CollectionsUpdates listUpdates() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(x -> x * 2);
        System.out.println(numbers);

        return this;
    }
}
