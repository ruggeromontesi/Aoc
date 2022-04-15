package it.ruggero.adventofcode2021.day1.streamsolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    List<Window> windows = new ArrayList<>();

    public Solution(String filePath) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextInt()) {
                windows.add(new Window(scanner.nextInt()));
            }
        } catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public  long calculateIncreases() {
        return windows.stream().skip(1).filter(window -> window.getSum() > window.getNBehind(1).getSum()).count();

    }

    public long CalculateIncreasesWindow() {
        windows = windows.stream().skip(2).map( window ->
            new Window(window.getNBehind(2).getSum(), window.getNBehind(1).getSum(), window.getSum())
        ).collect(Collectors.toList());
        return windows.stream().skip(1).filter( window -> window.getSum() > window.getNBehind(1).getSum()).count();
    }

    class Window {
        private int sum = 0;
        public Window(int... values) {
            this.sum = Arrays.stream(values).sum();
        }
        public int getSum() {
            return sum;
        }
       public Window getNBehind(int n) {
           return (windows.indexOf(this)> n -1) ?  windows.get(windows.indexOf(this) - n) : null;
       }
    }
}