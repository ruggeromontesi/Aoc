package it.ruggero.adventofcode2021.day12;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CaveMapTest {
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day12\\test0Day12.txt";
    private static final String FILE_PATH_SIMPLE = ".\\src\\main\\resources\\day12\\testSimpleDay12.txt";
    private static final String FILE_PATH_TEST_1 = ".\\src\\main\\resources\\day12\\test1Day12.txt";
    private static final String FILE_PATH_TEST_2 = ".\\src\\main\\resources\\day12\\test2Day12.txt";
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day12\\Day12.txt";

    @Test
    public void testCreateCaveMap() {
        CaveMap caveMap = new CaveMap(FILE_PATH_TEST);

        caveMap.getCaves().forEach( (s, cave) -> System.out.println(cave));

    }


    @Test
    public void test00() {
        CaveMap caveMap = new CaveMap(FILE_PATH_SIMPLE);
        caveMap.drawPaths();
        caveMap.getPaths().forEach(path -> {
            path.getCaves().forEach(c-> System.out.print(c.getLabel()+","));
        });
        System.out.println("\n number of paths :" + caveMap.getPaths().size() );

    }

    @Test
    public void test0() {
        CaveMap caveMap = new CaveMap(FILE_PATH_TEST);
        caveMap.drawPaths();
        caveMap.getPaths().forEach(path -> {
            System.out.println("");
            path.getCaves().forEach(c-> System.out.print(c.getLabel()+","));
        });
        System.out.println("number of paths :" + caveMap.getPaths().size() );

    }


    @Test
    public void test1() {
        CaveMap caveMap = new CaveMap(FILE_PATH_TEST_1);
        caveMap.drawPaths();
        caveMap.getPaths().forEach(path -> {
            System.out.println("");
            path.getCaves().forEach(c-> System.out.print(c.getLabel()+","));
        });
        System.out.println("number of paths :" + caveMap.getPaths().size() );

    }

    @Test
    public void test2() {
        CaveMap caveMap = new CaveMap(FILE_PATH_TEST_2);
        caveMap.drawPaths();
        caveMap.getPaths().forEach(path -> {
            System.out.println("");
            path.getCaves().forEach(c-> System.out.print(c.getLabel()+","));
        });
        System.out.println("number of paths :" + caveMap.getPaths().size() );

    }

    @Test
    public void testActual() {
        CaveMap caveMap = new CaveMap(FILE_PATH_ACTUAL);
        caveMap.drawPaths();
        caveMap.getPaths().forEach(path -> {
            System.out.println("");
            path.getCaves().forEach(c-> System.out.print(c.getLabel()+","));
        });
        //PART 1

        Assertions.assertEquals(5576,caveMap.getPaths().size() );
        //PART 2

        Assertions.assertEquals(152837,caveMap.getPaths().size() );


        System.out.println("number of paths :" + caveMap.getPaths().size() );

    }



}
