package net.mypieceofthe.java8.java8inaction.C2_streams;

import net.mypieceofthe.java8.java8inaction.domain.dishes.Dish;
import net.mypieceofthe.java8.java8inaction.domain.dishes.DishDomainUsing;

import java.util.*;
import java.util.function.Function;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by kgolebiowski on 30/04/2017.
 */
public class C6_3_Grouping extends DishDomainUsing {

    public static void main(String[] args) {

        // By simple field
        Map<Dish.Type, List<Dish>> dishesByType =
                getDishList().stream().collect(groupingBy(Dish::getType));

        System.out.println(dishesByType);

        // By more complicated lambda expression
        Function<Dish, String> dishByCaloryDivisionFunction = dish -> {
            if (dish.getCalories() <= 400)
                return "DIET";
            else
                return "NORMAL";
        };

        Map<String, List<Dish>> dishesByCaloricType =
                getDishList().stream()
                        .collect(groupingBy(dishByCaloryDivisionFunction));

        System.out.println(dishesByCaloricType);

        // Multilevel
        Map<Dish.Type, Map<String, List<Dish>>> dishesByTypeAndCaloric =
                getDishList().stream()
                        .collect(groupingBy(Dish::getType, groupingBy(dishByCaloryDivisionFunction)));

        System.out.println(dishesByTypeAndCaloric);

        // -- Collecting data in subgroups

        Map<Dish.Type, Long> typesCounts =
                getDishList().stream().collect(groupingBy(Dish::getType, counting()));

        System.out.println(typesCounts);

        Map<Dish.Type, IntSummaryStatistics> typesCaloryStats =
                getDishList().stream().collect(groupingBy(Dish::getType, summarizingInt(Dish::getCalories)));

        System.out.println(typesCaloryStats);

        // Get most caloric in each group
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                getDishList().stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));

        System.out.println(mostCaloricByType);

        // Return custom type (Dish instead of Optional which is useless here)
        Map<Dish.Type, Dish> mostCaloricByTypeNoOptional =
                getDishList().stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)), Optional::get)
                        ));

        System.out.println(mostCaloricByTypeNoOptional);

        Map<Dish.Type, Integer> typesCaloryStatsAvg =
                getDishList().stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(summarizingInt(Dish::getCalories), IntSummaryStatistics::getMin)));

        System.out.println(typesCaloryStatsAvg);

        // -- Mapping

        Map<Dish.Type, Set<String>> caloricLevelsByType =
                getDishList().stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return "DIET";
                                    else return "NORMAL";
                                }, toSet())));

        System.out.println(caloricLevelsByType);

        // Same using provided custom collection
        Map<Dish.Type, Set<String>> caloricLevelsByTypeColl =
                getDishList().stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return "DIET";
                                    else return "NORMAL";
                                },
                                toCollection(HashSet::new))));

        System.out.println(caloricLevelsByTypeColl);
    }
}
