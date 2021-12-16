
package it.ruggero.adventofcode2021.day6;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaySix {
    private static final String filePath =".\\src\\main\\resources\\day6\\testFishes.txt";
    private static List<Integer> timerList = new ArrayList<>();

    public static List<Integer> getTimerList() {
        return timerList;
    }

    public static void readFile(String filePath) {
        String line ="";
        try (Scanner scanner = new Scanner(new File(filePath)) ) {
            while (scanner.hasNext()) {
                line += scanner.nextLine();
            }

        } catch(FileNotFoundException ex) {
            System.out .println(ex);
        }


        String[] timers = line.split(",");
        for(String string : timers) {
            try {
                int timer = Integer.parseInt(string.trim());
                timerList.add(timer);
            } catch (NumberFormatException ex) {
                System.out.println(ex);
            }
        }

    }

    public static void process (int days) {
        for(int i =0 ; i <timerList.size(); i++) {

        }

        for( int timer : timerList) {

        }
    }

    public static void timerNextDay() {
        List<Integer> newFishes = new ArrayList<>();
        for(int i= 0; i < timerList.size(); i++) {
            int timer = timerList.get(i);
            if( timer > 0) {
                timer --;
            } else {
                newFishes.add(8);
                timer = 6;
            }
            timerList.set(i,timer);

        }

        timerList.addAll(newFishes);

    }
}
