package it.ruggero.adventofcode2021.day10;

import it.ruggero.adventofcode2021.day10.entity.Line;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

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

    public long calculateAllMissingCharacterScore() {
        lines.forEach( l -> l.calculateMissingCharacterScore());
        List<Line> incompleteLines = lines.stream().filter( l -> l.isIncomplete()).collect(Collectors.toList());
        incompleteLines.stream().forEach(l -> l.calculateMissingCharacterScore());
        Collections.sort(incompleteLines, Comparator.comparingLong( l-> l.getMissingCharacterScore()));
        incompleteLines.forEach( line -> System.out.println(line.getMissingCharacterScore()));
        int index = incompleteLines.size()/2;
        System.out.println("total  " + incompleteLines.size());
        System.out.println("index middle  " + index);
        System.out.println("score " + incompleteLines.get(index).getMissingCharacterScore());
        return incompleteLines.get(index).getMissingCharacterScore();


    }
}
