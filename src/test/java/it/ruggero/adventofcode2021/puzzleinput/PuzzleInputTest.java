/*
 * Copyright (c) Dematic GmbH 2021. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.puzzleinput;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author montesr
 */
public class PuzzleInputTest {

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
