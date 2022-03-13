package it.ruggero.adventofcode2021.day11;

import it.ruggero.adventofcode2021.day11.entity.Octopus;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

public class TestOctopusGrid {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day11\\testDay11.txt";
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day11\\day11.txt";

    @Test
    public void createOctopusGridTest() {
        OctopusGrid octopusGrid = new OctopusGrid(FILE_PATH_TEST);
        octopusGrid = new OctopusGrid(FILE_PATH_ACTUAL);

    }

    @Test
    public void printOctopusGridTest() {
        OctopusGrid octopusGrid = new OctopusGrid(FILE_PATH_TEST);
        octopusGrid.printGrid();
        octopusGrid = new OctopusGrid(FILE_PATH_ACTUAL);
        octopusGrid.printGrid();
    }


    @Test
    public void subStepOneTest() {
        OctopusGrid octopusGrid = new OctopusGrid(FILE_PATH_TEST);
        octopusGrid.printGrid();
        octopusGrid.subStepOne();
        octopusGrid.printGrid();
        octopusGrid = new OctopusGrid(FILE_PATH_ACTUAL);
        octopusGrid.printGrid();
    }

    @Test
    public  void testGetNeighbour() {
        OctopusGrid octopusGrid = new OctopusGrid(FILE_PATH_TEST);
        Octopus[][] map = octopusGrid.getMap();

        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                octopusGrid.getNeighbour(map[row][column],Direction.NORTH);

            }
        }

    }

    @Test
    public void testDoAction() {
        OctopusGrid octopusGrid = new OctopusGrid(FILE_PATH_TEST);
        //octopusGrid.octopusAccept(o -> System.out.println(o.getEnergyLevel()));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.NORTH));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.NORTHEAST));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.EAST));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.SOUTHEAST));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.SOUTH));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.SOUTHWEST));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.WEST));
        octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,Direction.NORTHWEST));
        for(Direction direction : Direction.values()) {
            octopusGrid.octopusUnaryOperator( o -> octopusGrid.getNeighbour(o,direction));
        }
    }


    @Test
    public void testGetAllNeighbours() {
        OctopusGrid octopusGrid = new OctopusGrid(FILE_PATH_TEST);
        Octopus[][] map = octopusGrid.getMap();
        System.out.println(octopusGrid.getAllNeighbours(map[1][0]));
        octopusGrid.getAllNeighbours(map[1][0]);
        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                octopusGrid.getAllNeighbours(map[row][column]);


            }
        }

    }

    @Test
    public void testFlashSingleOctopus(){
        OctopusGrid octopusGrid = new OctopusGrid(FILE_PATH_TEST);
        octopusGrid.printGrid();
        octopusGrid.flashSingleOctopus(octopusGrid.getMap()[1][1]);
        octopusGrid.printGrid();

    }


}
