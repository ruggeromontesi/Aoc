package it.ruggero.adventofcode2021.day15.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.part1.ChitonContext.buildFromFile;
import static it.ruggero.adventofcode2021.day15.part1.ChitonContext.getCavernMap;

public class MatrixMappingTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";




    @Test
    void shouldDo() {
        buildFromFile(FILE_PATH_TEST);
        var cavernMap = getCavernMap();
        var out = MatrixMapping.extendMatrix(cavernMap);
        int size = out.length;
        Assertions.assertTrue(size > 5);
    }


}
