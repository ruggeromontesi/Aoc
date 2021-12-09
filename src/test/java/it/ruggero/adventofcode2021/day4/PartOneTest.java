/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day4;

import org.junit.jupiter.api.Test;

/**
 * @author montesr
 */
public class PartOneTest {

    @Test
    public void testReadFile() {
        PartOne.readFile();
        PartOne.processData();
        for(int a : PartOne.getCalledNumbers()) {
            System.out.println(a + "\n");
        }
    }
}
