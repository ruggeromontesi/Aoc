package it.ruggero.adventofcode2021.day11;

import it.ruggero.adventofcode2021.day11.entity.Octopus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class OctopusGrid {

    private Octopus[][] map;

    private int numberOfRows = 0;

    private int numberOfColumns = 0 ;

    private int totalOfNumberFlashes = 0;

    private int timeOfSimultaneousFlashing = -1;

    public OctopusGrid(String filePath) {
        String line;

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();
                numberOfRows++;
                if (numberOfColumns == 0) {
                    this.numberOfColumns = line.length();

                } else {
                    if (numberOfColumns != line.length()) {
                        throw new RuntimeException("Inconsistent map!");
                    }

                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        map = new Octopus[numberOfRows][numberOfColumns];

        try (Scanner input = new Scanner(new File(filePath))) {
            int j = 0;
            while (input.hasNextLine()) {
                line = input.nextLine();
                for(int i = 0; i < this.numberOfColumns; i++) {
                    int energyLevel = 0;
                    try {
                        energyLevel = Integer.parseInt(line.substring(i, i+1));
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong map!");
                    }
                    map[j][i] = new Octopus(j,i,energyLevel);
                }

                j++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }


    }

    public Octopus[][] getMap() {
        return map;
    }

    public int getTotalOfNumberFlashes() {
        return totalOfNumberFlashes;
    }

    public void printGrid() {
        for(int  row = 0; row < map.length; row++) {
            System.out.println("");
            for(int column = 0; column < map[row].length; column++) {
                System.out.print(String.format("%3d",map[row][column].getEnergyLevel()) );
                //System.out.print(map[row][column].getEnergyLevel());
            }
        }
        System.out.println("");
    }

    public void subStepOne() {
        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                map[row][column].increaseEnergyLevelByOne();
            }
        }

    }

    public int getTimeOfSimultaneousFlashing() {
        return timeOfSimultaneousFlashing;
    }

    public void subStepTwo(int stepNumber) {
        flashAllOctopuses(stepNumber);



    }


    public Octopus getNeighbour(Octopus thisOctopus, Direction direction) {
        int row = thisOctopus.getRow();
        int column = thisOctopus.getColumn();
        if(direction == Direction.NORTH) {
            if (row > 0) {
                return map[row-1][column];
            }
        }
        if(direction == Direction.NORTHEAST) {
            if(row > 0 && column < map[row-1].length - 1) {
                return map[row-1][column+1];
            }
        }
        if(direction == Direction.EAST) {
            if(column < map[row].length - 1) {
                return map[row][column+1];
            }
        }
        if (direction == Direction.SOUTHEAST) {
            if(row < map.length - 1 && column < map[row+1].length - 1) {
                return  map[row+1][column+1];
            }
        }

        if(direction == Direction.SOUTH) {
            if( row < map.length - 1) {
                return map[row+1][column];
            }
        }

        if (direction == Direction.SOUTHWEST) {
            if(row < map.length - 1 && column > 0) {
                return map[row+1][column-1];
            }
        }

        if(direction == Direction.WEST) {
            if(column > 0) {
                return map[row][column-1];
            }
        }

        if(direction == Direction.NORTHWEST) {
            if(row  > 0 && column >0) {
                return map[row-1][column-1];
            }
        }




        return null;
    }

    public void octopusAccept(Consumer<Octopus> consumer) {
        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                consumer.accept(map[row][column]);
            }
        }

    }


    public void octopusUnaryOperator(UnaryOperator<Octopus> unaryOperator) {
        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                unaryOperator.apply(map[row][column]);

            }
        }

    }

    public Set<Octopus> getAllNeighbours(Octopus octopus) {
        Set<Octopus> neighbouringOctopuses = new TreeSet<>();
        for(Direction direction : Direction.values()) {
            Octopus neighbouringOctopus = getNeighbour(octopus,direction);
            if(neighbouringOctopus != null) {
                neighbouringOctopuses.add(neighbouringOctopus);
            }
        }

        return neighbouringOctopuses;
    }

    public boolean flashSingleOctopus(Octopus octopus, int stepNumber){
        if(octopus.getEnergyLevel() < 10) {
            return false;
        }

        if(octopus.getFlashesOccurred().get(stepNumber) != null && octopus.getFlashesOccurred().get(stepNumber)) {
            return false;
        }
        Set<Octopus> neighbouringOctopuses = getAllNeighbours(octopus);
        for(Octopus  neighbouringOctopus : neighbouringOctopuses) {
            neighbouringOctopus.increaseEnergyLevelByOne();
        }
        octopus.getFlashesOccurred().put(stepNumber,true);
        octopus.setTobeFlashedInThisStep(false);
        return true;

    }




    public  int flashAllOctopuses(int stepNumber) {

        int numberOfFlashesOccurred = 0;
        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                if(flashSingleOctopus(map[row][column],stepNumber) ) {
                    numberOfFlashesOccurred++;
                }

            }
        }
        return  numberOfFlashesOccurred;

    }


    public void resetEnergyofOctopusesWhichFlashedIntThisStep(int stepNumber){
        octopusAccept(octopus -> {
            if (octopus.getFlashesOccurred().get(stepNumber)) {
                octopus.resetenergyLevel();
            }
        });
    }


    public void step(int stepNumber) {
        subStepOne();
        octopusAccept(octopus -> {
            octopus.getFlashesOccurred().put(stepNumber,false);
        });
        flashAllOctopuses(stepNumber);

        int flashes = flashAllOctopuses(stepNumber);

        while(flashes != 0) {
            flashes = flashAllOctopuses(stepNumber);
        }
        resetEnergyofOctopusesWhichFlashedIntThisStep(stepNumber);





    }


    public void doMultipleSteps(int numberOfSteps) {
        for (int i = 0; i < numberOfSteps; i++) {
            step(i);
            if (checkIfAllOctopusesAreFlashing()) {
                timeOfSimultaneousFlashing = i+1;
                System.out.println("timeOfSimultaneousFlashing  " + timeOfSimultaneousFlashing);
                break;
            }


        }

    }


    public void calculateTotalNumberOfSteps() {
        int total = 0;
        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                map[row][column].calculateTotalOfNumberFlashes();
                total += map[row][column].getTotalOfNumberFlashes();

            }
        }

        totalOfNumberFlashes = total;


    }


    public boolean checkIfAllOctopusesAreFlashing() {
        boolean output = false;

        for(int  row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length; column++) {
                if ( map[row][column].getEnergyLevel() != 0) {
                    return false;
                }


            }
        }

        return true;


    }




}
