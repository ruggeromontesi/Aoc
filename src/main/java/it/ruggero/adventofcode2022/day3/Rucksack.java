package it.ruggero.adventofcode2022.day3;

import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.ruggero.adventofcode2022.util.ParseFileUtility.getLines;
import static it.ruggero.adventofcode2022.util.ParseFileUtility.readFile;

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


    public static int partOne() {
        readFile(FILE_PATH);
        return getLines().stream().mapToInt(Rucksack::priorityOfItemAppearingInBothCompartments).sum();
    }

}
