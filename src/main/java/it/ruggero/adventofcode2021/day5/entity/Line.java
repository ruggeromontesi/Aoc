package it.ruggero.adventofcode2021.day5.entity;

import it.ruggero.adventofcode2021.day5.support.Help;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Line {
    private Coordinate startingPoint;
    private Coordinate endingPoint;
    private double slope;
    private int integerSlope;
    private boolean isVertical;
    private boolean isHorizontal;
    private List<Coordinate> coordinateList = new ArrayList<>();
    private boolean hasOnlyIntegerCoordinates ;

    public Line(Coordinate coordinate1, Coordinate coordinate2) {

        this.startingPoint = (coordinate1.getX() < coordinate2.getX())?(coordinate1):(coordinate2);
        this.endingPoint = (coordinate1.getX() < coordinate2.getX() )?(coordinate2 ):(coordinate1);
        calculateSlope();
        checkIfHasOnlyIntegerCoordinates();
        createCoordinateList();
    }

    public Coordinate getStartingPoint() {
        return startingPoint;
    }

    public Coordinate getEndingPoint() {
        return endingPoint;
    }

    public double getSlope() {
        return slope;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public boolean isHasOnlyIntegerCoordinates() {
        return hasOnlyIntegerCoordinates;
    }


    public boolean isVertical() {
        return isVertical;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    private void calculateSlope( ) {
        if(startingPoint.getX() != endingPoint.getX()) {
            slope = (double)(endingPoint.getY()-startingPoint.getY())/(endingPoint.getX()-startingPoint.getX());
            isHorizontal = false;
        } else{
            isVertical = true;
        }

        if(startingPoint.getY() == endingPoint.getY()) {
            isHorizontal = true;
        }

    }



    private void checkIfHasOnlyIntegerCoordinates( ){
        if( isHorizontal || isVertical) {
            hasOnlyIntegerCoordinates = true;
            integerSlope = (int) slope;
            return;
        }

        if (Help.isInteger(slope) || Help.isInteger(1/slope)) {
            hasOnlyIntegerCoordinates = true;
            integerSlope = (int) slope;
            return;
        }
        hasOnlyIntegerCoordinates = false;

    }


    private void createCoordinateList(){
        if( isVertical) {
            int maxY = (endingPoint.getY() > startingPoint.getY())?(endingPoint.getY()):(startingPoint.getY());
            int minY = (startingPoint.getY()  < endingPoint.getY())?(startingPoint.getY()):(endingPoint.getY());
            for(int i = minY; i < maxY+1; i++) {
                coordinateList.add(new Coordinate(startingPoint.getX(),i));
            }
            return;
        }

        if( isHorizontal) {
            int maxX = (endingPoint.getX() > startingPoint.getX())?(endingPoint.getX()):(startingPoint.getX());
            int minX = (startingPoint.getX() < endingPoint.getX())?(startingPoint.getX()):(endingPoint.getX());
            for(int i = minX; i < maxX+1; i++) {
                coordinateList.add(new Coordinate(i, startingPoint.getY()));
            }
            return;
        }


        if( !isHorizontal && !isVertical) {
            Coordinate startingCoordinate = (startingPoint.getX() < endingPoint.getX())?(startingPoint):(endingPoint);
            Coordinate endingCoordinate = (startingPoint.getX() < endingPoint.getX())?(endingPoint ):(startingPoint);
            for(int i = startingCoordinate.getX(); i < endingCoordinate.getX() +1; i++) {
                coordinateList.add(new Coordinate(i, startingCoordinate.getY()+(i -startingCoordinate.getX()) *integerSlope));
            }
            return;

        }
    }

    public void print() {
        coordinateList.stream().forEach(System.out::println);
    }

    public boolean containsThisCoordinate(Coordinate coordinate) {
        if( isHorizontal) {
            if(coordinate.getY() == startingPoint.getY()) {
                return true;
            } else {
                return false;
            }
        }

        if( isVertical ) {
            if( coordinate.getY() == startingPoint.getY()) {
                return true;
            } else {
                return  false;
            }
        }

        if ( (coordinate.getY()-startingPoint.getY())/(coordinate.getX() - startingPoint.getX()) == slope) {
            return true;
        } else {
            return false;
        }


    }







}
