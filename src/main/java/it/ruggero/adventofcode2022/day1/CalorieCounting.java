package it.ruggero.adventofcode2022.day1;

import it.ruggero.util.input.FilePathResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

/***
 * https://codereview.stackexchange.com/questions/81852/empty-line-delimiter-single-line-output
 */

public class CalorieCounting {

    private final FilePathResolver filePathResolver = new FilePathResolver(1, 2022);

    private List<String> read() throws IOException {
        var a = filePathResolver.getSampleInputFilePath();
        Path source = Paths.get(a);
        String poem = new String(Files.readAllBytes(source));
        Pattern para = Pattern.compile("\\s*^\\s*$\\s*", Pattern.MULTILINE);
        boolean b = Pattern.matches("^a*b.*$", "abbondanza");
        boolean c = Pattern.matches("^rug.*$", "ruggero");

        String d = "ruggeroamontesianelamezzoadelacammina";
        Pattern pattern = Pattern.compile("\\r\\n\\r\\n");
        var e = pattern.split(poem);
        return null;

    }

    public static void main(String[] args) throws IOException {
        new CalorieCounting().read();
    }




}
