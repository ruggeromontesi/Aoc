package it.ruggero.adventofcode2022.day9;

import it.ruggero.util.input.FilePathResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9ReadFile {
    private static final FilePathResolver filePathResolver = new FilePathResolver(9, 2022);

    private static List<MotionInstruction> read(String filePath)  {
        String input = null;
        try {
            input = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Pattern pattern = Pattern.compile("\\r\\n");
        return Stream.of(pattern.split(input)).map(s -> {
            var values = Pattern.compile("\\s").split(s);
            return MotionInstruction
                    .builder()
                    .direction(Direction.valueOf(values[0]))
                    .steps(Integer.parseInt(values[1]))
                    .build();
        }).collect(Collectors.toList());
    }

    public  static List<MotionInstruction> read() {
        return read(filePathResolver.getInputFilePath());
    }

    public  static List<MotionInstruction> readSample() {
        return read(filePathResolver.getSampleInputFilePath());
    }

    public static void main(String[] args)  {
        Day9ReadFile.read();
    }
}
