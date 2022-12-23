package it.ruggero.adventofcode2022.day5;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.ruggero.util.input.old.ParseFileUtility.getLinesFromFile;

public class SupplyStacks {

    private static final String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day5\\Day5.txt";
    private static final UnaryOperator<String> removeNonAlphabeticalChars = s -> s.replace("[", "")
            .replace("]", "").replace(" ", "");

    @Getter
    private static ArrayDeque<Character>[] stacks;

    @Getter
    private static List<Instruction> instructions = new ArrayList<>();

    private static String actualFilepath;

    public static void runPartOne(final String... filepath) {
        runCommon(filepath);
        applyInstructions(applyInstructionPartOne);
    }

    public static void runPartTwo(final String... filepath) {
        runCommon(filepath);
        applyInstructions(applyInstructionPartTwo);
    }

    private static void runCommon(String[] filepath) {
        actualFilepath = filepath.length == 0 ? FILE_PATH : filepath[0];
        buildStacks();
        retrieveInstructions();
    }

    private static void buildStacks() {
        List<String> stackLines = createAndInitializeStacks();
        populateStacks(stackLines);

    }

    @NotNull
    private static List<String> createAndInitializeStacks() {
        var stackLines = getLinesFromFile(actualFilepath).stream()
                .filter(s -> s.contains("[")).collect(Collectors.toList());

        int numberOfStacks = stackLines.stream().map(removeNonAlphabeticalChars).mapToInt(String::length).max().orElseThrow();
        stacks = new ArrayDeque[numberOfStacks];

        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new ArrayDeque<>();
        }
        return stackLines;
    }

    private static void populateStacks(List<String> stackLines) {
        stackLines.forEach(line -> {
            for (int n = 0; 4 * n + 1 < line.length() && n < stacks.length; n++) {
                char ch = line.charAt(4 * n + 1);
                if (Character.isLetter(ch)) {
                    stacks[n].add(ch);
                }

            }
        });
    }

    private static void retrieveInstructions() {
        var instructionLines = getLinesFromFile(actualFilepath).stream()
                .filter(s -> s.contains("move")).collect(Collectors.toList());

        instructions = instructionLines.stream().map(s -> {

            String[] chunks = s.split(" ");

            var instr = Arrays.stream(chunks).map(str -> {
                try {
                    return Integer.parseInt(str);
                } catch (RuntimeException e) {
                    return -10;
                }
            }).filter(i -> i > -1).collect(Collectors.toList());


            return Instruction.builder()
                    .quantityToMove(instr.get(0))
                    .stackFromId(instr.get(1))
                    .stackToId(instr.get(2))
                    .build();

        }).collect(Collectors.toList());
    }

    private static void applyInstructions(Consumer<Instruction> applyInstruction) {
        instructions.forEach(applyInstruction);
    }
    private static final Consumer<Instruction> applyInstructionPartOne = instruction ->
            IntStream.range(0, instruction.quantityToMove).forEach(i -> {
        var out = stacks[instruction.getStackFromId() - 1].pop();
        stacks[instruction.getStackToId() - 1].push(out);
    });

    private static final Consumer<Instruction> applyInstructionPartTwo = instruction -> {
        ArrayDeque<Character> temp = new ArrayDeque<>();
        IntStream.range(0, instruction.quantityToMove)
                .forEach(i -> temp.push(stacks[instruction.getStackFromId() - 1].pop()));

        IntStream.range(0, temp.size())
                .forEach(i -> stacks[instruction.getStackToId() - 1].push(temp.pop()));
    };

    public static String whatCratesEndsUpOnTopOfEachStack() {
        return Arrays.stream(stacks).map(ArrayDeque::peek)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    @Builder
    @Data
    public static class Instruction {
        private int quantityToMove;
        private int stackFromId;
        private int stackToId;
    }

}
