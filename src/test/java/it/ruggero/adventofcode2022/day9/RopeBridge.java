package it.ruggero.adventofcode2022.day9;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

public class RopeBridge {
    
    private Point head;
    private Point tail;
    
    private String filePath;
    
    private List<Point> tailPointList = new ArrayList<>();

    public void moveHead(Direction d) {
        switch (d) {
            case U:
                head =  new Point(head.x, head.y + 1);
                break;
            case D:
                head = new Point(head.x, head.y - 1);
                break;
            case L:
                head = new Point(head.x - 1, head.y);
                break;
            case R:
                head = new Point(head.x + 1, head.y);
                break;
        }
    }
    
    public void moveTail() {
        tailPointList.add(tail);
        if(head.x - tail.x == 2 && head.y == tail.y) {
            tail = new Point(tail.x + 1, tail.y);
        }
        if(head.x - tail.x == -2 && head.y == tail.y) {
            tail = new Point(tail.x - 1, tail.y);
        }
        if (head.y - tail.y == 2 && head.x == tail.x) {
            tail = new Point(tail.x, tail.y + 1); 
        }
        if (head.y - tail.y == -2 && head.x == tail.x) {
            tail = new Point(tail.x, tail.y - 1);
        }
  
        if(Math.abs(head.x - tail.x ) + Math.abs(head.y - tail.y) == 2) {
            tail = new Point(head.x, head.y);
        }
    }
    
    
    
    
    
    @With
    @Builder
    @Data
    static class Point {
        private int x;
        private int y;
    }
    
    enum Direction {
        U,D,R,L
    }
}
