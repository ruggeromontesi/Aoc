package it.ruggero.adventofcode2022.Day10.input;

import it.ruggero.adventofcode2022.Day10.operations.AbstractOperation;
import it.ruggero.adventofcode2022.Day10.operations.Add;
import it.ruggero.adventofcode2022.Day10.operations.GenericOperation;
import it.ruggero.adventofcode2022.Day10.operations.NoOp;
import it.ruggero.util.input.FilePathResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10ReadFile {

    private static final String NO_OP = "noop";
    private static final String ADD_OP = "addx";

    private static final FilePathResolver filePathResolver = new FilePathResolver(10, 2022);


    public static List<? extends GenericOperation> read(String filepath) {

        String input = null;
        try {
            input = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pattern pattern = Pattern.compile("\\r\\n");
        return Stream.of(pattern.split(input)).map(Day10ReadFile::parseString).collect(Collectors.toList());
    }


    public static List<? extends GenericOperation> read() {
        return read(filePathResolver.getInputFilePath());
    }

    public static List<? extends GenericOperation> readSample() {
        return read(filePathResolver.getSampleInputFilePath());
    }


    public static AbstractOperation parseString(String s) {

        if (s.contains(NO_OP)) {
            return new NoOp();
        }

        if (s.contains(ADD_OP)) {
            return new Add(s);
        }


        return null;
    }


}
