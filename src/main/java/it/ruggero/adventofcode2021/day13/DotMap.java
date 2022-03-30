package it.ruggero.adventofcode2021.day13;


import it.ruggero.adventofcode2021.day13.entity.Coordinate;
import it.ruggero.adventofcode2021.day13.entity.Dot;
import it.ruggero.adventofcode2021.day13.entity.FoldInstruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DotMap {
    Map<Coordinate, Dot> map = new TreeMap<>();
    private final List<FoldInstruction> foldInstructions ;
    private final List<DotMap> nextMaps = new ArrayList<>();

    private int maxX = 0;
    private int maxY = 0;

    public DotMap() {
        foldInstructions = new ArrayList<>();
    }

    public DotMap(String filePath ) {
        foldInstructions = new ArrayList<>();

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (!line.startsWith("fold") && !line.equals("")) {
                    String coordinates[] = line.split(",");
                    int x = Integer.parseInt(coordinates[0]);
                    if (x > maxX) {
                        maxX = x;
                    }

                    int y = Integer.parseInt(coordinates[1]);
                    if (y > maxY) {
                        maxY = y;
                    }



                    Coordinate coordinate = new Coordinate(x,y);
                    Dot dot = new Dot(coordinate,"#");
                    map.put(coordinate,dot);

                }

                if (line.contains("fold along")) {
                    FoldInstruction foldInstruction = null;
                    int value = Integer.parseInt(line.substring(line.length()-1));
                    if (line.contains("x")) {
                      foldInstruction = new FoldInstruction(FoldInstruction.Direction.y, value);
                    }

                    if(line.contains("y")) {
                        foldInstruction = new FoldInstruction(FoldInstruction.Direction.x, value);
                    }
                    foldInstructions.add(foldInstruction);
                }

            }
        } catch (FileNotFoundException
                ex) {
            System.out.println(ex);
        }

        for(int row = 0 ; row < maxY+1; row++){
            for(int column = 0; column < maxX+1; column++) {
                Coordinate coordinate = new Coordinate(column,row);
                Dot dot = map.get(coordinate);
                if (dot == null) {
                    dot = new Dot(coordinate, ".");
                    map.put(coordinate,dot );
                }
            }
        }


        if (maxY == 893) {
            for (int column = 0; column < maxX + 1; column++) {
                Coordinate coordinate = new Coordinate(column, maxY + 1);
                Dot dot = new Dot(coordinate,".");
                map.put(coordinate,dot );

            }
        }
        maxY++;

    }

    public Map<Coordinate, Dot> getMap() {
        return map;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public List<FoldInstruction> getFoldInstructions() {
        return foldInstructions;
    }

    public List<DotMap> getNextMaps() {
        return nextMaps;
    }

    public void printMap() {
        for (int row = 0; row < maxY+1; row ++) {
            System.out.println("");
            for (int column = 0; column < maxX+1; column++) {
                System.out.print(map.get((new Coordinate(column,row))).getSymbol());
            }
        }

        System.out.println("\nrows : " + (maxY + 1) + " columns " + (maxX + 1));

    }

    public DotMap processFoldInstruction(FoldInstruction foldInstruction) {
        //validateFoldingInstruction(foldInstruction);
        DotMap outputDotMap = new DotMap();
        if(foldInstruction.getDirection() == FoldInstruction.Direction.x) {
            outputDotMap.maxX = this.maxX;
            outputDotMap.maxY = this.maxY/2 -1;

            for (int row = 0; row < outputDotMap.maxY + 1; row ++) {
                for (int column = 0; column < outputDotMap.maxX+1; column++) {
                    Coordinate coordinate = new Coordinate(column,row);
                    Coordinate coordinateUp = new Coordinate(column,row);
                    Coordinate coordinateLow = new Coordinate(column,this.maxY - row);
                    //Coordinate coordinateLow = new Coordinate(column,2 * foldInstruction.getValue() - row);
                    String symbol ="";
                    if(map.get(coordinateUp).getSymbol().equals("#") || map.get(coordinateLow).getSymbol().equals("#")) {
                        symbol = "#";
                    } else {
                        symbol = ".";
                    }
                    Dot dot = new Dot(coordinate,symbol);
                    outputDotMap.map.put(coordinate ,dot);

                }
            }

        }

        if (foldInstruction.getDirection() == FoldInstruction.Direction.y) {
            outputDotMap.maxX = this.maxX/2 - 1;
            outputDotMap.maxY = this.maxY;
            for (int row = 0; row < outputDotMap.maxY + 1; row ++) {
                for (int column = 0; column < outputDotMap.maxX + 1; column++) {
                    Coordinate coordinate = new Coordinate(column,row);
                    Coordinate coordinateLeft = new Coordinate(column,row);
                    Coordinate coordinateRight = new Coordinate(this.maxX - column,row);
                    String symbol ="";
                    if(map.get(coordinateLeft).getSymbol().equals("#") || map.get(coordinateRight).getSymbol().equals("#")) {
                        symbol = "#";
                    } else {
                        symbol = ".";
                    }
                    Dot dot = new Dot(coordinate,symbol);
                    outputDotMap.map.put(coordinate ,dot);

                }
            }



        }


        return outputDotMap;
    }

    public void validateFoldingInstruction(FoldInstruction foldInstruction){
        if (foldInstruction.getDirection() == FoldInstruction.Direction.x) {
            for (int column = 0; column < maxX + 1; column ++) {
                if (map.get(new Coordinate(column,foldInstruction.getValue())).getSymbol().equals("#") ) {
                    throw new RuntimeException("dots on folding line!");
                } else {
                    map.get(new Coordinate(column,foldInstruction.getValue())).setSymbol("-");
                }

            }
        }

        if (foldInstruction.getDirection() == FoldInstruction.Direction.y) {
            for (int row = 0; row < maxY + 1; row++) {
                if (map.get(new Coordinate(foldInstruction.getValue(),row)).getSymbol().equals("#")) {
                    throw new RuntimeException("dots on folding line!");
                } else {
                    map.get(new Coordinate(foldInstruction.getValue(),row)).setSymbol("|");
                }
            }
        }

    }




    public void processAllFoldInstructions() {
        DotMap lastDotMap  = this;
        for(FoldInstruction foldInstruction :foldInstructions) {
            DotMap nextDotMap = lastDotMap.processFoldInstruction(foldInstruction);
            nextMaps.add(nextDotMap);
            lastDotMap = nextDotMap;
        }
    }

    public int countDots(){
        return (int) map.entrySet().stream().filter(coordinateDotEntry ->
            coordinateDotEntry.getValue().getSymbol().equals("#")
        ).count();
    }

}
