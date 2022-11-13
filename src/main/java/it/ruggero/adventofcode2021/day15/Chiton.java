package it.ruggero.adventofcode2021.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class Chiton {

    final private Map<Coordinate,Position> map = new TreeMap<>();

    private  int nColumns;

    private  int nRows;

    public Chiton(final String filePath) {
        try {
            Scanner input = new Scanner(new File(filePath));
            int row = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                nColumns = line.length();
                int finalRow = row;
                IntStream.range(0,line.length()).forEach(i -> {
                    Coordinate coordinate = new Coordinate(i,finalRow);
                    Position position = new Position(coordinate, Integer.valueOf(line.substring(i,i+1)));
                    map.put(coordinate,position);
                });

                row++;

            }

            nRows = row - 1 ;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

    }

    public Map<Coordinate, Position> getMap() {
        return map;
    }


    public void print() {
        for(int row = 0; row < nRows; row++) {
            System.out.println("");
            for(int column = 0; column < nColumns; column++) {
                System.out.print("[" + map.get(new Coordinate(column,row)).getRiskLevel() + "]");
            }

        }
    }

    public Position getNeighbour(Coordinate thisCoordinate ,Direction direction) {

        Position position = null;
        Coordinate neighbourCoordinate = null;
        int thisX = thisCoordinate.getX();
        int thisY = thisCoordinate.getY();

        if(direction == Direction.NORTH) {
            neighbourCoordinate = new Coordinate(thisX, thisY-1 );
        }

        if(direction == Direction.EAST) {
            neighbourCoordinate = new Coordinate(thisX + 1, thisY );
        }

        if(direction == Direction.SOUTH) {
            neighbourCoordinate = new Coordinate(thisX , thisY + 1 );
        }

        if(direction == Direction.WEST) {
            neighbourCoordinate = new Coordinate(thisX - 1, thisY  );
        }

        return map.get(neighbourCoordinate);
    }

    public List<Position> navigateOneStep(Position position, Path path) {
        List<Position> candidatePositions = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Position neighbourPosition  = map.get(getNeighbour(position.getCoordinate(),direction));
            if (position != null ) {
                candidatePositions.add(neighbourPosition);
            }
        }

        if (path.getLastPosition() != null) {
            candidatePositions.remove(path.getLastPosition());
        }

        candidatePositions.sort(Comparator.comparingInt(Position::getRiskLevel));
        int min = candidatePositions.stream().min(Comparator.comparingInt(Position::getRiskLevel)).get().getRiskLevel();
        candidatePositions.removeIf( p -> p.getRiskLevel() > min);

        return  candidatePositions;

    }

}
