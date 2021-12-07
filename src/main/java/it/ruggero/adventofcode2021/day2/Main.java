package it.ruggero.adventofcode2021.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private final static int SIZE = 1000;
    private static String[] directions = new String[SIZE];
    private static int[] distance = new int[SIZE];

    public static void readFile()  {
        Scanner scanner;
        int i = 0;

        final String filePath =  "C:\\Projects\\adventOfCode2021\\src\\main\\resources\\day2\\directions.txt";
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
        int aim[] = new int[SIZE];
        aim[0] = 0;

        for (int i = 0; i< SIZE; i++ ) {
            int deltaX = 0;
            int deltaY = 0;
            int deltaAim  = 0;
            System.out.print( i + "\t\t\t"+ x + "\t\t\t" + y + "\t\t" + aim[i] + "\t\t\t"   + directions[i].substring(0,2) + "\t\t\t" + distance[i] );
            if ( directions[i].equals("up")) {
                //y -= distance[i];
                deltaY = -distance[i];
                if(i != 0) {
                    //aim[i] = aim[i-1] - distance[i];
                    deltaAim = - distance[i];
                }


            }

            if ( directions[i].equals("down")) {
                //y += distance[i];
                deltaY = distance[i];
                if(i != 0) {
                    //aim[i] = aim[i-1] + distance[i];
                    deltaAim =  distance[i];
                }


            }

            if(i != 0) {
                aim[i] = aim[i-1] +deltaAim;
            }


            if ( directions[i].equals("forward")) {
                //x += distance[i];
                deltaX = distance[i];
                int oldY = y;
                if(i != 0) {
                    aim[i] = aim[i-1] ;
                    deltaY = distance[i]*aim[i];
                }
            }

            x = x + deltaX;
            y = y + deltaY;

            System.out.print("\t\t\t"+ deltaX + "\t\t\t"+ deltaY + "\t\t\t" + deltaAim +  "\t\t\t"   + x + "\t\t\t" + y   + "\t\t\t" + aim[i]  +"\n");

        }

        return  x*y;

    }


}
