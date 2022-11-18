package it.ruggero.adventofcode2021.day15.domain;

import it.ruggero.adventofcode2021.day15.readfile.ParseFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static it.ruggero.adventofcode2021.day15.domain.ChitonContext.*;
import static it.ruggero.adventofcode2021.day15.domain.ChitonContext.Direction.NORTH;

class ChitonContextTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";
    private static final String FILE_PATH = ".\\src\\main\\resources\\day15\\Day15.txt";

    @Test
    void shouldCreateChitonContextTestFile() {
        buildFromFile(FILE_PATH_TEST);
        Assertions.assertFalse(() -> getRiskMap().isEmpty());
        //ChitonContext.printRiskMap();
    }


    @Test
    void shouldCreateChitonContextMainFile() {
        buildFromFile(FILE_PATH);
        Assertions.assertFalse(() -> getRiskMap().isEmpty());


    }

    @Test
    void shouldFindNeighbourNorth() {
        buildFromFile(FILE_PATH_TEST);
        var startCoordinate = new ChitonContext.Coordinate(3,0);

        var endCoordinate = NORTH.findNeighbour(startCoordinate);
        Assertions.assertEquals(endCoordinate.orElseThrow(),new ChitonContext.Coordinate(2,0) );
    }

    @Test
    void shouldNotFindNeighbourNorth(){
        buildFromFile(FILE_PATH_TEST);
        var startCoordinate = new ChitonContext.Coordinate(0,0);
        var endCoordinate = NORTH.findNeighbour(startCoordinate);
        Assertions.assertTrue(endCoordinate.isEmpty());
    }
//
//    @Test
//    void shouldNavigateEast(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        chitonContext.move(ChitonContext.Direction.EAST);
//        Assertions.assertEquals(chitonContext.getCurrentCordinate(),new ChitonContext.Coordinate(0,1) );
//    }
//
//    @Test
//    void shouldNotNavigateEast(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        var b = chitonContext.getRiskMap();
//        chitonContext.getCurrentCordinate().setCol(chitonContext.getWIDTH()-1);
//        chitonContext.move(ChitonContext.Direction.EAST);
//        Assertions.assertEquals(chitonContext.getCurrentCordinate(),new ChitonContext.Coordinate(0,chitonContext.getWIDTH()-1) );
//    }
//
//    @Test
//    void shouldNavigateSouth(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        chitonContext.move(ChitonContext.Direction.SOUTH);
//        Assertions.assertEquals(chitonContext.getCurrentCordinate(),new ChitonContext.Coordinate(1,0) );
//    }
//
//    @Test
//    void shouldNotNavigateSouth(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        chitonContext.getCurrentCordinate().setRow(chitonContext.getHEIGHT()-1);
//        chitonContext.move(ChitonContext.Direction.SOUTH);
//        Assertions.assertEquals(chitonContext.getCurrentCordinate(),new ChitonContext.Coordinate(chitonContext.getHEIGHT()-1,0) );
//    }
//
//
//    @Test
//    void shouldNavigateWest(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        chitonContext.getCurrentCordinate().setCol(1);
//        chitonContext.move(ChitonContext.Direction.WEST);
//        Assertions.assertEquals(chitonContext.getCurrentCordinate(),new ChitonContext.Coordinate(0,0) );
//    }
//
//    @Test
//    void shouldNotNavigateWest(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        chitonContext.move(ChitonContext.Direction.WEST);
//        Assertions.assertEquals(chitonContext.getCurrentCordinate(),new ChitonContext.Coordinate(0,0) );
//    }
//
//
//    @Test
//    void shouldChooseDirection(){
//        var a =new ParseFile(FILE_PATH_TEST);
//        var chitonContext = new ChitonContext(a.getLines());
//        chitonContext.printRiskMap();
//        var dir = chitonContext.chooseDirection(new ChitonContext.Coordinate(0,2));
//        System.out.println(" dir : " + dir);
//    }

}
