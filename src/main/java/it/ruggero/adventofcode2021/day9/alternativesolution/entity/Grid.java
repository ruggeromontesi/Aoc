package it.ruggero.adventofcode2021.day9.alternativesolution.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grid {

    private int gridMaxX = 0;

    private int gridMaxY = 0;

    private int sumOfRiskLevelsOFAllLowPoints = 0;

    private Point[][] map ;

    public int getSumOfRiskLevelsOFAllLowPoints() {
        return sumOfRiskLevelsOFAllLowPoints;
    }

    public Point[][] getMap() {
        return map;
    }


    public Grid(String filePath) {

        String line;

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();
                gridMaxY++;
                if (gridMaxX == 0) {
                    this.gridMaxX = line.length();

                } else {
                    if (gridMaxX != line.length()) {
                        throw new RuntimeException("Inconsistent map!");
                    }

                }
            }
        } catch (FileNotFoundException
                ex) {
            System.out.println(ex);
        }

        map = new Point[gridMaxY][gridMaxX];

        try (Scanner input = new Scanner(new File(filePath))) {
            int j = 0;
            while (input.hasNextLine()) {
                line = input.nextLine();
                for(int i = 0; i < this.gridMaxX; i++) {
                    int height = 0;
                    try {
                        height = Integer.parseInt(line.substring(i, i+1));
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong map!");
                    }
                    map[j][i] = new Point(height);
                }

                j++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        checkIfPointsAreLow();
        calculateSumOfRiskLevelsOFAllLowPoints();

    }


    public void printGrid() {
        for(int j = 0; j < map.length; j++) {
            System.out.println("");
            for(int i = 0; i < map[j].length; i++) {
                System.out.print(map[j][i].getHeight() + " ");
            }
        }
    }

    public void printLowPoints() {
        for(int j = 0; j < map.length; j++) {
            System.out.println("");
            for(int i = 0; i < map[j].length; i++) {
                if(map[j][i].isLow() ) {
                    System.out.print(map[j][i].getHeight() + " ");
                } else {
                    System.out.print("* ");
                }

            }
        }
    }




    public void isPointLow(int x, int y ) {
        Point thisPoint  = map[y][x];

        //NORTH BORDER
        if (y == 0) {
            //NORTH BORDER NOT CORNER
            if (x > 0 && x < gridMaxX -1) {
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                return;
            }
            //NORTH WEST CORNER
            if(x == 0) {
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                return;
            }
            //NORTH EAST CORNER
            if(x == gridMaxX-1) {
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                return;
            }


        }

        //SOUTH BORDER
        if (y == gridMaxY -1 ) {
            //SOUTH BORDER NOT CORNER
            if (x > 0 && x < gridMaxX -1) {
                //CHECK NORTH NEIGHBOUR
                if (!isLowerThanNorthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }

                thisPoint.setLow(true);
                return;
            }
            //SOUTH WEST CORNER
            if(x == 0) {
                //CHECK NORTH NEIGHBOUR
                if (!isLowerThanNorthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK EAST NEIGHBOUR
                if (!isLowerThanEastNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                return;
            }
            //SOUTH EAST CORNER
            if(x == gridMaxX-1) {
                //CHECK WEST NEIGHBOUR
                if (!isLowerThanWestNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                //CHECK SOUTH NEIGHBOUR
                if(!isLowerThanSouthNeighbour(x,y)) {
                    thisPoint.setLow(false);
                    return;
                }
                thisPoint.setLow(true);
                return;
            }


        }

        //ON WEST BORDER NOT IN CORNERS
        if (x == 0 && y < gridMaxY - 1 ) {
            //CHECK NORTH NEIGHBOUR
            if (!isLowerThanNorthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK EAST NEIGHBOUR
            if (!isLowerThanEastNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK SOUTH NEIGHBOUR
            if(!isLowerThanSouthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            thisPoint.setLow(true);
            return;
        }


        //ON EAST BORDER NOT IN CORNERS
        if (x == gridMaxX -1  && y < gridMaxY - 1 ) {
            //CHECK NORTH NEIGHBOUR
            if (!isLowerThanNorthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK WEST NEIGHBOUR
            if (!isLowerThanWestNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK SOUTH NEIGHBOUR
            if(!isLowerThanSouthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            thisPoint.setLow(true);
            return;

        }

        //ALL OTHER CASES
        //CHECK NORTH NEIGHBOUR
        if( x > 0 && x < gridMaxX -1 && y > 0 && y < gridMaxY - 1) {
            if (!isLowerThanNorthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK EAST NEIGHBOUR
            if (!isLowerThanEastNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK SOUTH NEIGHBOUR
            if(!isLowerThanSouthNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }
            //CHECK WEST NEIGHBOUR
            if (!isLowerThanWestNeighbour(x,y)) {
                thisPoint.setLow(false);
                return;
            }

            thisPoint.setLow(true);
            return;
        }
    }

    private void checkIfPointsAreLow() {
        for( int j = 0; j < map.length; j++){
            for(int i = 0; i < map[j].length; i++) {
                isPointLow(i,j);
            }
        }
    }


    private boolean isLowerThanNorthNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y-1][x].getHeight()) {
            return false;
        } else {
            return  true;
        }
    }

    private boolean isLowerThanEastNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y][x+1].getHeight()) {
            return false;
        } else {
            return  true;
        }

    }

    private boolean isLowerThanSouthNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y+1][x].getHeight()) {
            return false;
        } else {
            return  true;
        }

    }

    private boolean isLowerThanWestNeighbour(int x ,int y) {
        if (map[y][x].getHeight() >= map[y][x-1].getHeight()) {
            return false;
        } else {
            return  true;
        }

    }


    public void calculateSumOfRiskLevelsOFAllLowPoints( ) {
        int result = 0;
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[j].length; i++) {
                if ( map[j][i].isLow()) {
                    result += map[j][i].getRiskLevel();
                }
            }
        }

        sumOfRiskLevelsOFAllLowPoints = result;


    }





}
