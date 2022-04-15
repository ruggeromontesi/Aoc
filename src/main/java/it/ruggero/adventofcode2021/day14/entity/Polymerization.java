/*
 * Copyright (c) Dematic GmbH 2022. All rights reserved. Confidential.
 */
package it.ruggero.adventofcode2021.day14.entity;

import java.io.File;
import java.util.Scanner;

/**
 * @author montesr
 */
public class Polymerization {
    private String polymerTemplate;

    public Polymerization(String filePath) {
        try {
            Scanner input = new Scanner(new File(filePath));
            while (input.hasNextLine()) {
                String inputLine = input.nextLine();
            }


        } catch (Exception ex) {

        }
    }
}
