
package it.ruggero.adventofcode2021.day5.entity;

import it.ruggero.adventofcode2021.day5.DayFive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineTest {

    @Test
    public void testIsVertical( ){
        Line line = new Line(new Coordinate(370,142), new Coordinate( 370,700));
        Assertions.assertTrue(line.isVertical());
        Assertions.assertFalse(line.isHorizontal());
    }


    @Test
    public void testIsHorizontal( ){
        Line line = new Line(new Coordinate(200,142), new Coordinate( 400,142));
        Assertions.assertTrue(line.isHorizontal());
    }

    @Test
    public void createCoordinateListTest(){
        //370,142 -> 370,700
        Line line = new Line(new Coordinate(370,142), new Coordinate( 370,700));
        line.print();
    }


    @Test
    public void testContainsThisCoordinate() {
        Line line2 = new Line(new Coordinate(2,1), new Coordinate(2,2));
        line2.containsThisCoordinate(new Coordinate(2,0));
        Assertions.assertTrue(line2.containsThisCoordinate(new Coordinate(2,0)));
        /*Line line = new Line(new Coordinate(8,0), new Coordinate(0,8));
        line.containsThisCoordinate(new Coordinate(7,1));
        Assertions.assertTrue(line.containsThisCoordinate(new Coordinate(7,1)));
        Assertions.assertTrue(line.containsThisCoordinate(new Coordinate(6,2)));
        Assertions.assertTrue(line.containsThisCoordinate(new Coordinate(5,3)));
        Assertions.assertTrue(line.containsThisCoordinate(new Coordinate(4,4)));
        String filePath = ".\\src\\main\\resources\\day5\\day5test.txt";
        filePath =  ".\\src\\main\\resources\\day5\\day5.txt";
        DayFive.readFile(filePath);
        for(Line lineA : DayFive.getLineList()){
            for(Coordinate coordinate : lineA.getCoordinateList()) {
                Assertions.assertTrue(lineA.containsThisCoordinate(coordinate));
            }

        }*/




    }


    @Test
    public void testVerticalLine() {
        Line line = new Line(new Coordinate(7,0), new Coordinate(7,4));
        Assertions.assertTrue(line.containsThisCoordinate(new Coordinate(7,1)));

    }

}
