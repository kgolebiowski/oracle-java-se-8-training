package net.mypieceofthe.java8.java8inaction.AppB_MiscLibraryUpdates;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by kgolebiowski on 06/05/2017.
 */
public class CollectionsUpdates {

    private Map<String, Integer> createNumbersMap() {
        Map<String, Integer> numbersMap = new HashMap<>();
        numbersMap.put("One", 1);
        numbersMap.put("Two", 2);
        numbersMap.put("Three", 3);
        numbersMap.put("Four", 4);

        return numbersMap;
    }

    public static void main(String[] args) {
        new CollectionsUpdates()
                .mapUpdates()
                .mapMerge()
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

        System.out.println();

        return this;
    }

    private CollectionsUpdates mapMerge() {
        BiFunction<Integer, Integer, Integer> mappingFunction = (i1, i2) -> {
            System.out.println("Calling mappingFunction with " + i1 + ", " + i2);
            return i1 + i2;
        };

        Map<String, Integer> numbersMap = createNumbersMap();

        Integer result = numbersMap.merge("Eleven", 11, mappingFunction); // Does not exist in map
        System.out.println("Result is " + result + " and final map: ");
        System.out.println(numbersMap);

        result = numbersMap.merge("One", 900, mappingFunction); // This does exist
        System.out.println("Result is " + result + " and final map: ");
        System.out.println(numbersMap);

        numbersMap.put("Twenty", null);
        result = numbersMap.merge("Twenty", 20, mappingFunction); // Merge with existing null value
        System.out.println(result);

        System.out.println();

        return this;
    }

    private CollectionsUpdates listUpdates() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(x -> x * 2);
        System.out.println(numbers);

        return this;
    }
}
