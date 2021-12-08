/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author montesr
 */
public class PartTwoTest {

    @Test
    public void readFileTest()  {
        PartTwo.readFile();
        Assertions.assertEquals(1000,PartTwo.getReadingsList().size());

    }

    @Test
    public void processListTest() {
        PartTwo.readFile();
        PartTwo.processList( 0, "mostCommonBit");
        Assertions.assertEquals(7, PartTwo.getSelectedReadingsList().size());

    }

    @Test
    public void testCalculateOxygenGeneratorRating( ){
        PartTwo.readFile();
        int oxigenGeneratorRating = PartTwo.calculateOxygenGeneratorRating();
        Assertions.assertEquals(oxigenGeneratorRating,23);

    }


    @Test
    public void testCalculateCO2ScrubberRating( ){
        PartTwo.readFile();
        int CO2ScrubberRating = PartTwo.calculateCO2ScrubberRating();
        Assertions.assertEquals(CO2ScrubberRating,10);

    }

    @Test
    public void testCalculateLifeSupportRating( ) {
        PartTwo.readFile();
        int oxigenGeneratorRating = PartTwo.calculateOxygenGeneratorRating();
        int CO2ScrubberRating = PartTwo.calculateCO2ScrubberRating();

        System.out.println("oxigenGeneratorRating  " + oxigenGeneratorRating);
        System.out.println("CO2ScrubberRating " + CO2ScrubberRating);
        System.out.println( " LifeSupportRating : " + oxigenGeneratorRating*CO2ScrubberRating );
    }

}
