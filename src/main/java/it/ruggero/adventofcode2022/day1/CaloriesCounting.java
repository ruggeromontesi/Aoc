package it.ruggero.adventofcode2022.day1;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class CaloriesCounting {

    @Getter
    private static Map<Integer,List<Integer>> calories = new HashMap<>();

    @Getter
    private static Map<Integer,Integer> caloriesCount = new TreeMap<>();

    private static int elfId = 0;

    public static void createMapElfCalories(List<String> lines) {

        List<Integer> caloriesList = new ArrayList<>();
        for(String line : lines) {
            if(line.length() > 0) {
                caloriesList.add(Integer.parseInt(line));
            } else {
                calories.put(elfId,caloriesList);
                caloriesList = new ArrayList<>();
                elfId++;
            }
        }
        calories.put(elfId,caloriesList);
    }

    public static void countCalories() {
        caloriesCount = calories.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToInt(i -> i).sum()
                        )
        );
    }

}
