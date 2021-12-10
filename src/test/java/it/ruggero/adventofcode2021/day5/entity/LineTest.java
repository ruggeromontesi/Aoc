
package it.ruggero.adventofcode2021.day5.entity;

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
}
