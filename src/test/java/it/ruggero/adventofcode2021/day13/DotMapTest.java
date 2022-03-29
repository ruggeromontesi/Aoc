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
        FoldInstruction foldInstruction = dotMap.getFoldInstructions().get(0);
        System.out.println("\ndummy");
        DotMap newDotMap = dotMap.processFoldInstruction(foldInstruction);
        newDotMap.printMap();
        System.out.println("\nprocessed map has " + newDotMap.countDots() + " dots");
        DotMap newDotMap2 = newDotMap.processFoldInstruction(dotMap.getFoldInstructions().get(1));
        newDotMap2.printMap();
        System.out.println("\nprocessed map has " + newDotMap2.countDots() + " dots");


    }

    @Test
    public void testFoldActualFile(){
        DotMap dotMap = new DotMap(FILE_PATH_ACTUAL);
        FoldInstruction foldInstruction = dotMap.getFoldInstructions().get(0);
        System.out.println("\ndummy");
        DotMap newDotMap = dotMap.processFoldInstruction(foldInstruction);
        System.out.println("\nprocessed map has " + newDotMap.countDots() + " dots");

    }


    @Test
    public void testAllTestFile(){
        DotMap dotMap = new DotMap(FILE_PATH_TEST);
        dotMap.processAllFoldInstructions();
        dotMap.getNextMaps().get(dotMap.getNextMaps().size()-1).printMap();


    }

    @Test
    public void testAllActualFile(){
        DotMap dotMap = new DotMap(FILE_PATH_TEST);
        dotMap.processAllFoldInstructions();
        //dotMap.getNextMaps().get(dotMap.getNextMaps().size()-1).printMap();
        dotMap.getNextMaps().stream().skip(0L).forEach(dotMap1 -> {
            System.out.println("\n*******************************************");
            dotMap1.printMap();
        });


    }
}
