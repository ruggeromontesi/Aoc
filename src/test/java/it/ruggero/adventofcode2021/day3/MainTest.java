package it.ruggero.adventofcode2021.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void testPreliminaryScan( ){
        Assertions.assertEquals(12,Main.preliminaryScan() );


    }

    @Test
    public void testReadFile() {
        Main.preliminaryScan();
        Main.readFile();
    }

    @Test
    public void testGammaRate() {
        Main.preliminaryScan();
        Main.readFile();
        //Assertions.assertEquals(22,Main.gammaRate() );
        //Assertions.assertEquals(9,Main.epsilonRate() );
        System.out.println("gamma rate  is " + Main.gammaRate());
        System.out.println("epsilon rate  is " + Main.epsilonRate());
        System.out.println("result = " + Main.gammaRate()*Main.epsilonRate());
        Assertions.assertEquals(1540244,Main.gammaRate()*Main.epsilonRate() );
        //Main.processFile();

    }

    @Test
    public void testBinaryStringToDecimal( ) {
        String in = "10001";
        Assertions.assertEquals(17, Main.binaryStringToDecimal(in));
    }
}
