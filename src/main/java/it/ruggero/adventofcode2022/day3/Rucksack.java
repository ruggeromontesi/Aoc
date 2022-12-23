package it.ruggero.adventofcode2022.day3;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.ruggero.util.input.old.ParseFileUtility.*;
import static it.ruggero.util.input.old.ParseFileUtility.readFile;

public class Rucksack {

    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day3\\Day3.txt";

    private Rucksack() {
    }

    public static Set<Character> findItemsInBothCompartments(@NotNull String compartments) {

        int middleIndex = compartments.length() / 2;

        if (middleIndex * 2 != compartments.length()) {
            throw new RuntimeException(" String has not an even number of characters");
        }

        var firstCompartment = compartments.substring(0, middleIndex);
        var secondCompartment = compartments.substring(middleIndex);
        var itemsInFirstCompartment = IntStream.range(0, firstCompartment.length())
                .mapToObj(firstCompartment::charAt).collect(Collectors.toList());
        var itemsInSecondCompartment = IntStream.range(0, secondCompartment.length())
                .mapToObj(secondCompartment::charAt).collect(Collectors.toList());
        return itemsInFirstCompartment.stream().filter(itemsInSecondCompartment::contains).collect(Collectors.toSet());

    }

    public static int priorityOfItemAppearingInBothCompartments(@NotNull String compartments) {
        char ch = findItemsInBothCompartments(compartments).stream().findFirst().orElseThrow();
        if (ch < 92) {
            return ch - 38;
        } else {
            return ch - 96;
        }
    }

    public static int itemPriority(char ch) {
        if (ch < 92) {
            return ch - 38;
        } else {
            return ch - 96;
        }
    }

    public static int partOne() {
        readFile(FILE_PATH);
        return getLines().stream().mapToInt(Rucksack::priorityOfItemAppearingInBothCompartments).sum();
    }

    public static List<List<String>> splitIntoGroups() {
        readFile(FILE_PATH);
        List<List<String>> out = new ArrayList<>();
        int i = 0;
        List<String> group = new ArrayList<>();
        for(String line : getLines()) {
            group.add(line);
            if(i++ == 2) {
                out.add(group);
                group = new ArrayList<>();
                i = 0;
            }
        }
        return out;
    }

    public static char findCommonItemsInGroup(List<String>  groups) {
        if(groups.size() != 3) {
            throw new RuntimeException();
        }

        var itemsInFirstString = IntStream.range(0, groups.get(0).length()).mapToObj(groups.get(0)::charAt).collect(Collectors.toList());

        var x =itemsInFirstString.stream().filter(
                c ->
                        groups.stream().allMatch(s -> s.indexOf(c) > -1)
        ).collect(Collectors.toSet());

        if(x.size() > 1) {
            throw new RuntimeException("!!!!!!!!!!!!!!!!11");
        }

        return x.stream().findFirst().orElseThrow();
    }

    public static List<Character> findCommonItemsInGroups(List<List<String>>  groups) {

        List<Character> items = new ArrayList<>();

        for(List<String> group : groups) {
            var a = items.add(findCommonItemsInGroup(group));
            if(!a) {
                System.out.println("elemento non inserito");
            }

        }
        return groups.stream().map(Rucksack::findCommonItemsInGroup).collect(Collectors.toList());

    }
    public static int partTwo() {
        var y = findCommonItemsInGroups(splitIntoGroups());
        return y.stream().mapToInt(Rucksack::itemPriority).sum();
    }
}
