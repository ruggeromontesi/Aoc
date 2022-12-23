package it.ruggero.adventofcode2022.day8;

import it.ruggero.util.input.FilePathResolver;
import it.ruggero.util.input.FileToInteger2DArray;

public class TreetopTreeHouse  {

    private int[][] heightMap;

    private final FilePathResolver filePathResolver = new FilePathResolver(8,2022);


    private void buildHeightMap() {
        heightMap = new FileToInteger2DArray(filePathResolver).read();
    }

}
