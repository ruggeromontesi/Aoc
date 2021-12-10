/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day5.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author montesr
 */
public class HelpTest {

    @Test
    public void isIntegerTest() {
        double value = 12.005;
        Assertions.assertEquals(false, Help.isInteger(12.005));
        Assertions.assertEquals(true, Help.isInteger(12.00));
        //Help.isInteger(value);

    }
}
