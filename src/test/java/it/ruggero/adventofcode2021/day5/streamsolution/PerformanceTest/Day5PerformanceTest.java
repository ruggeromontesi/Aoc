package it.ruggero.adventofcode2021.day5.streamsolution.PerformanceTest;

import it.ruggero.adventofcode2021.day5.streamsolution.entity.HydrotermalVentureMap;
import it.ruggero.adventofcode2021.day5.streamsolution.entity.concurrent.CalculateNumberOfOverlappingLinesAction;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Day5PerformanceTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day5\\HydrotermalVentureMapInputSampleCase.txt";
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day5\\HydrotermalVentureMapInput.txt";

    @Test

    public void performanceTestBigCase() {

        HydrotermalVentureMap hydrotermalVentureMap = new HydrotermalVentureMap(FILE_PATH_ACTUAL);
        ForkJoinTask<?> task = new CalculateNumberOfOverlappingLinesAction(hydrotermalVentureMap,0,
                hydrotermalVentureMap.getMapWithNumberOfOverlappingLines().length *
                        hydrotermalVentureMap.getMapWithNumberOfOverlappingLines()[0].length );
        ForkJoinPool pool  = new ForkJoinPool();
        Long start = System.currentTimeMillis();
        pool.invoke(task);
        Long stop = System.currentTimeMillis();

        System.out.println("The number of points where at least two lines overlap is " + hydrotermalVentureMap.getNumberOfPointsWhereAtLeastTwoLinesOverlap());
       //
        System.out.println("Execution time in ms with concurrency "+ (stop - start));

        hydrotermalVentureMap = new HydrotermalVentureMap(FILE_PATH_ACTUAL);
        start = System.currentTimeMillis();
        hydrotermalVentureMap.createMapWitNumberOfOverlappingLines();
        stop = System.currentTimeMillis();
        Assert.assertEquals(21466,hydrotermalVentureMap.getNumberOfPointsWhereAtLeastTwoLinesOverlap());
        System.out.println("Execution time in ms without concurrency "+ (stop - start));




    }

}
