/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author montesr
 */
public class Day8Test {

    @Test
    public void testConvert( ){
        String testString = "fdgacbe cefdb cefbgd gcbe";
        testString = "cefdb cefdb cefbgd gcbe";
        String[] digits = testString.trim().split(" ");
        Day8.convert(digits);
        //Assertions.assertEquals(8,Day8.convert(digits));

    }

    @Test
    public void testCreateMapping( ){
        String testString =  "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf";
        testString = "begfcd fabg aecgbdf cefagb edgcba eacbf efgbc bca ab decfa | cbgef befdcg bceaf fagb";
        Day8.createMapping(testString);


    }


    @Test
    public void testSecondPart( ){
        Day8.secondPart();



    }


}
