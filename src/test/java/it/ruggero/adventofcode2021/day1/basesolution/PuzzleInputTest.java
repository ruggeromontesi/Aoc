/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day1.basesolution;

import it.ruggero.adventofcode2021.day1.basesolution.PuzzleInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author montesr
 */
public class PuzzleInputTest {
   @BeforeAll
   public static void setup() {
      PuzzleInput.readFile();
   }


   @Test
   public void testReadFile() {
      Assertions.assertEquals( PuzzleInput.processSimple(), 1342);
   }


   @Test
   public void testProcessWindow() {
      PuzzleInput.processWindow();
      Assertions.assertEquals(PuzzleInput.processWindow(), 1378);
   }
}
