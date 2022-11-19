package it.ruggero.adventofcode2021.day15.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.domain.ChitonContext.*;
import static it.ruggero.adventofcode2021.day15.domain.ChitonContext.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class ChitonContextTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";
    @BeforeAll
    static void build() {
        buildFromFile(FILE_PATH_TEST);
    }

    @Test
    void shouldCreateChitonContextTestFile() {
        Assertions.assertFalse(() -> getCavernMap().isEmpty());
    }

    @Test
    void shouldCreateChitonContextMainFile() {
        Assertions.assertFalse(() -> getCavernMap().isEmpty());
    }

    @Test
    void shouldFindNeighbourNorth() {
        var startCoordinate = new ChitonContext.Coordinate(3,0);
        var endCoordinate = NORTH.findNeighbour(startCoordinate);
        assertEquals(endCoordinate.orElseThrow(),new ChitonContext.Coordinate(2,0) );
    }

    @Test
    void shouldNotFindNeighbourNorth(){
        var startCoordinate = new ChitonContext.Coordinate(0,0);
        var endCoordinate = NORTH.findNeighbour(startCoordinate);
        assertTrue(endCoordinate.isEmpty());
    }

    @Test
    void shouldFindNeighbourEast(){
        var startCoordinate = new ChitonContext.Coordinate(0,0);
        var endCoordinate = EAST.findNeighbour(startCoordinate);
        assertEquals(endCoordinate.orElseThrow(), new Coordinate(0,1));
    }

    @Test
    void shouldNotFindNeighbourEast(){
        var startCoordinate = new ChitonContext.Coordinate(0,getWIDTH());
        var endCoordinate = EAST.findNeighbour(startCoordinate);
        assertTrue(endCoordinate.isEmpty());

    }

    @Test
    void shouldFindNeighbourSouth(){
        var startCoordinate = new ChitonContext.Coordinate(0,0);
        var endCoordinate = SOUTH.findNeighbour(startCoordinate);
        assertEquals(endCoordinate.orElseThrow(), new Coordinate(1,0));
    }

    @Test
    void shouldNotFindNeighbourSouth(){
        var startCoordinate = new ChitonContext.Coordinate(getHEIGHT(),0);
        var endCoordinate = SOUTH.findNeighbour(startCoordinate);
        assertTrue(endCoordinate.isEmpty());
    }

    @Test
    void shouldFindNeighbourWest(){
        var startCoordinate = new ChitonContext.Coordinate(0,1);
        var endCoordinate = WEST.findNeighbour(startCoordinate);
        assertEquals(endCoordinate.orElseThrow(), new Coordinate(0,0));
    }

    @Test
    void shouldNotFindNeighbourWest(){
        var startCoordinate = new ChitonContext.Coordinate(0,0);
        var endCoordinate = WEST.findNeighbour(startCoordinate);
        assertTrue(endCoordinate.isEmpty());
    }


//    @Test
//    void shouldChooseDirection(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        chitonContext.printRiskMap();
//        var dir = chitonContext.chooseDirection(new ChitonContext.Coordinate(0,2));
//        System.out.println(" dir : " + dir);
//    }

}
