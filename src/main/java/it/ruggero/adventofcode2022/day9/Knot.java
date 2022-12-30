package it.ruggero.adventofcode2022.day9;

import lombok.Data;

@Data
class Knot {
    private int x;
    private int y;
    private String id;

    public Knot(int x, int y, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void move(Direction d) {
        switch (d) {
            case U:
                y++;
                break;
            case D:
                y--;
                break;
            case L:
                x--;
                break;
            case R:
                x++;
                break;
        }
    }

}
