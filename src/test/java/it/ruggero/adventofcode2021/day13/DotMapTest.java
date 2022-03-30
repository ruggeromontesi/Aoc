package it.ruggero.adventofcode2021.day13;

import it.ruggero.adventofcode2021.day13.entity.Coordinate;
import it.ruggero.adventofcode2021.day13.entity.FoldInstruction;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

public class DotMapTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day13\\testDay13.txt";
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day13\\Day13.txt";

    @Test
    public void testCreateDotMap(){
        DotMap dotMap = new DotMap(FILE_PATH_TEST);
        System.out.println("number of columns " + dotMap.getMaxX());
        System.out.println("number of rows " + dotMap.getMaxY());
        dotMap.printMap();

    }

    @Test
    public void testFold() {
        DotMap dotMap = new DotMap(FILE_PATH_TEST);
        dotMap.printMap();
        FoldInstruction foldInstruction = new FoldInstruction(FoldInstruction.Direction.x, 7);
        dotMap.validateFoldingInstruction(foldInstruction);
        System.out.println("\n\n\n");
        dotMap.printMap();
        System.out.println("\n\n\n");
        DotMap dotMap1 = dotMap.processFoldInstruction(foldInstruction);
        dotMap1.printMap();
        foldInstruction = new FoldInstruction(FoldInstruction.Direction.y, 5);
        dotMap1.validateFoldingInstruction(foldInstruction);
        System.out.println("\n\n\n");
        dotMap1.printMap();
        System.out.println("\n\n\n");
        dotMap1.processFoldInstruction(foldInstruction).printMap();

    }

    @Test
    public void testActual() {
        DotMap dotMap = new DotMap(FILE_PATH_ACTUAL);
        dotMap.processAllFoldInstructions();
        int lastIndex = dotMap.getNextMaps().size()-1;
        dotMap.getNextMaps().get(lastIndex).printMap();

    }


}
