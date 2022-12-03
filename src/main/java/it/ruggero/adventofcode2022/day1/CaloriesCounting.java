package it.ruggero.adventofcode2022.day1;

import it.ruggero.adventofcode2022.day1.common.ParseFileUtility;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static it.ruggero.adventofcode2022.day1.common.ParseFileUtility.readFile;

public class CaloriesCounting {

    private CaloriesCounting() {
    }

    @Getter
    private static Map<Integer,List<Integer>> calories = new HashMap<>();

    @Getter
    private static Map<Integer,Integer> caloriesCount = new TreeMap<>();

    private static int elfId = 0;

    public static void initialize(String filepath) {
        readFile(filepath);
        var a = ParseFileUtility.getLines();
        createMapElfCalories(a);
        countCaloriesByElf();
    }

    private static void createMapElfCalories(List<String> lines) {

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

    private static void countCaloriesByElf() {
        caloriesCount = calories.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToInt(i -> i).sum()
                        )
        );
    }

    public static int getTheAmountOfCaloriesFromTheElfCarryingTheMost(String filepath) {
        initialize(filepath);
        return getCaloriesCount().values().stream().max(Comparator.naturalOrder()).orElse(-1);
    }

    public static int getTheAmountOfCaloriesFromTheTopThreeElvesCarryingTheMost(String filepath) {
        initialize(filepath);
        Set<Integer> maxTreeValues = getCaloriesCount().values().stream().sorted((i1,i2) -> i2 -i1).limit(3)
                .collect(Collectors.toSet());



        return maxTreeValues.stream().mapToInt(i -> i).sum();
    }

}
