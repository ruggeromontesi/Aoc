package it.ruggero.adventofcode2021.day2.basesolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    //private final static int SIZE = 6;
    //private final static String filePath =  ".\\src\\main\\resources\\day2\\test.txt";
    private final static int SIZE = 1000;
    private final static String filePath =  ".\\src\\main\\resources\\day2\\directions.txt";
    private static String[] directions = new String[SIZE];
    private static int[] distance = new int[SIZE];

    public static void readFile()  {
        Scanner scanner;
        int i = 0;



        try {
            scanner = new Scanner(new File(filePath));
            System.out.println("scanning the file");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[ ] components = line.split(" ");
                directions[i] = components[0].trim();
                try {
                    distance[i] = Integer.parseInt(components[1].trim());
                } catch( NumberFormatException ex ) {
                    distance[i] = 0;

                }
                i++;

            }
        } catch(FileNotFoundException ex) {
            System.out.println("Exception caught!");
            System.out.println(ex);
        }

        //for( i = 0; i < SIZE; i++ ) {
            //System.out.println("i = " + i + " " + directions[i] + " " + distance[i]);
        //}

    }

    public static int processFile( ) {
        int x = 0;
        int y = 0;

        for (int i = 0; i< SIZE; i++ ) {
            //System.out.print( " i = " + i + " horizontal = " + x + " vertical = " + y +" direction = " + directions[i] + " distance = " + distance[i]);
            if ( directions[i].equals("up")) {
                y -= distance[i];

            }

            if ( directions[i].equals("down")) {
                y += distance[i];

            }

            if ( directions[i].equals("forward")) {
                x += distance[i];

            }
            //System.out.print(" -----> horizontal = " + x + " vertical = " + y +"\n");
        }

        return  x*y;
    }


    public static int processFileWithAim( ) {
        int x = 0;
        int y = 0;
        int aim = 0;

        for (int i = 0; i< SIZE; i++ ) {
            int deltaX = 0;
            int deltaY = 0;
            int deltaAim  = 0;
            System.out.print( i + "\t\t\t"+ x + "\t\t\t" + y + "\t\t\t" + aim + "\t\t\t"   + directions[i].substring(0,2) + "\t\t\t" + distance[i] );
            if ( directions[i].equals("up")) {

                //deltaY = -distance[i];
                deltaAim = - distance[i];

            }

            if ( directions[i].equals("down")) {
                //deltaY = distance[i];
                deltaAim =  distance[i];
            }

            if ( directions[i].equals("forward")) {
                deltaX = distance[i];
                deltaY = distance[i]*aim;
                deltaAim = 0;

            }

            x = x + deltaX;
            y = y + deltaY;
            aim =  aim + deltaAim;

            System.out.print("\t\t\t"+ deltaX + "\t\t\t"+ deltaY + "\t\t\t" + deltaAim +  "\t\t\t"   + x + "\t\t\t" + y   + "\t\t\t" + aim  +"\n");

        }

        return  x*y;

    }


}
