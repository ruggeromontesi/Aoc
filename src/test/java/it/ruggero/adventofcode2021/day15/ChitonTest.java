package it.ruggero.adventofcode2021.day15;

import org.junit.jupiter.api.Test;

public class ChitonTest {

    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day15\\testDay15.txt";

    @Test
    public void shouldReadFile(){

        Chiton chiton = new Chiton(FILE_PATH_TEST);
        System.out.println(chiton.getMap());
        chiton.print();

    }

    @Test
    public void testGetNeighbour() {
        Chiton chiton = new Chiton(FILE_PATH_TEST);
        Coordinate coordinate = new Coordinate(0,0);

        for(Direction direction : Direction.values()) {
            Position position = chiton.getNeighbour(coordinate,direction);
            if (position != null ) {
                System.out.println(chiton.getMap().get(position.getCoordinate())+ " ");
            }


        }

    }

    @Test
    public void testNavigateOneStep(){
        Chiton chiton = new Chiton(FILE_PATH_TEST);
        Coordinate coordinate = new Coordinate(0,0);
        Path path = new Path();
        //chiton.navigateOneStep();

    }
}
