package it.ruggero.adventofcode2021.day10.entity;

import it.ruggero.adventofcode2021.day10.NavigationSubsystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineTest {

    private static  final String FILE_PATH_CORRUPTED_LINES = ".\\src\\main\\resources\\day10\\corruptedLines.txt";

    @Test
    public void testParse( ){
        Line line = new Line("<([{}])>");
        line = new Line("[<>({}){}[([])<>]]");
        line.parse();

    }

    @Test
    public void testIsIncomplete() {
        Line line = new Line("[({(<(())[]>[[{[]{<()<>>");
        line.parse();
        Assertions.assertTrue(line.isIncomplete());

    }

    @Test
    public  void testIsCorrupted() {
        Line line = new Line("{([(<{}[<>[]}>{[]{[(<()>");
        line.parse();
        line = new Line("[[<[([]))<([[{}[[()]]]");
        line.parse();
        line = new Line("[{[{({}]{}}([{[{{{}}([]");
        line.parse();
        line = new Line("[<(<(<(<{}))><([]([]()");
        line.parse();
        line = new Line("<{([([[(<>()){}]>(<<{{");
        line.parse();



    }


    @Test
    public void testSeveralCorruptedLines() {
        NavigationSubsystem navigationSubsystem = new NavigationSubsystem(FILE_PATH_CORRUPTED_LINES);
        navigationSubsystem.parseAllLines();
        //navigationSubsystem.getLines().forEach( l -> System.out.println("is corrupted :" + l.isCorrupted()));
        navigationSubsystem.getLines().forEach(
                l -> Assertions.assertTrue(l.isCorrupted())

        );
    }
}
