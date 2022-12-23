package it.ruggero.adventofcode2022.day4;

import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.ruggero.util.input.old.ParseFileUtility.getLinesFromFile;

public class Cleanup {

    private Cleanup() {
    }

    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day4\\Day4.txt";

    @Getter
    private static final Predicate<PairSectionAssignment> oneRangeFullyContainsTheOther = p ->
            (p.getStartElfOne() > p.getStartElfTwo() - 1 && p.getStopElfOne() < p.getStopElfTwo() + 1) ||
                    (p.getStartElfOne() < p.getStartElfTwo() + 1 && p.getStopElfOne() > p.getStopElfTwo() - 1);

    @Getter
    private static final Predicate<PairSectionAssignment> areTheTwoRangesOverLapping = p -> {
        Set<Integer> firstElfRange = IntStream.range(p.getStartElfOne(), p.getStopElfOne() + 1).boxed().collect(Collectors.toSet());
        Set<Integer> secondElfRange = IntStream.range(p.getStartElfTwo(), p.getStopElfTwo() + 1).boxed().collect(Collectors.toSet());
        return firstElfRange.stream().anyMatch(secondElfRange::contains);
    };

    public static List<PairSectionAssignment> getAssignmentList(String... filepath) {
        String actualFilepath = (filepath.length == 0) ? FILE_PATH : filepath[0];
        List<String> inputLines = getLinesFromFile(actualFilepath);
        return inputLines.stream().map(Cleanup::convertStringToPairSectionAssignment).collect(Collectors.toList());
    }

    public static PairSectionAssignment convertStringToPairSectionAssignment(String input) {
        String[] index = input.split(",");
        String[] elfOneStrings = index[0].split("-");
        String[] elfTwoStrings = index[1].split("-");
        return PairSectionAssignment.builder()
                .startElfOne(Integer.parseInt(elfOneStrings[0]))
                .stopElfOne(Integer.parseInt(elfOneStrings[1]))
                .startElfTwo(Integer.parseInt(elfTwoStrings[0]))
                .stopElfTwo(Integer.parseInt(elfTwoStrings[1]))
                .build();
    }
}
