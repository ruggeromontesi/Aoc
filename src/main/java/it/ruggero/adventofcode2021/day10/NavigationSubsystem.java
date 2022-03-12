package it.ruggero.adventofcode2021.day10;

import it.ruggero.adventofcode2021.day10.entity.Line;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NavigationSubsystem {

    private List<Line> lines =new ArrayList<>();

    private int totalSyntaxErrorScore;

    public List<Line> getLines() {
        return lines;
    }

    public int getTotalSyntaxErrorScore() {
        return totalSyntaxErrorScore;
    }

    public NavigationSubsystem(String filePath) {
        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                lines.add(new Line(input.nextLine()));

            }
        } catch (FileNotFoundException
                ex) {
            System.out.println(ex);
        }
    }

    public void parseAllLines() {
        lines.forEach( l -> l.parse());
    }

    public  void calculateTotalSyntaxErrorScore() {
        totalSyntaxErrorScore = lines.stream().mapToInt(l -> l.getSintaxErrorScore()).reduce(0, (a,b) -> a+b);

    }
}
