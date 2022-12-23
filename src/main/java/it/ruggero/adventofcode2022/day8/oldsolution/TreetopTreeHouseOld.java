package it.ruggero.adventofcode2022.day8.oldsolution;


import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class TreetopTreeHouseOld {

    final static String FILE_PATH = ".\\src\\main\\resources\\adventofcode2022\\day8\\Day8.txt";
    final static String FILE_PATH_TEST = ".\\src\\main\\resources\\adventofcode2022\\day8\\testDay8.txt";

    @Getter
    private static int[][] heightMap;
    @Getter
    private static Tree[][] treeMap;
    private static final Set<Direction> directionsToSearch = Set.of(Direction.values());

    public static void main(String[] args) {
        buildFromFilePartOne(FILE_PATH_TEST);
        var tree =treeMap[1][1];
        var result = processSingleDirection.test(tree,Direction.SOUTH);
        System.out.println(result);
    }

    public static void buildFromFilePartOne(String filePath) {
        //heightMap  = parseFileAsIntArray(filePath);
        createTreeMap(heightMap);
    }

    final static BiPredicate<Tree,Direction> processSingleDirection = (Tree t, Direction direction) -> {
        List<Tree> allTreesTillEdgeInDirection = new ArrayList<>();
        //Tree nextTree;
        int[] nextTreeHeigth = new int[1];
        Optional<Coordinate> nextCoordinate;

        Tree[] currentTree = new Tree[1];

        currentTree[0] = t;

        do {
            nextCoordinate  = direction.findNeighbour(currentTree[0].getCoordinate());
            nextCoordinate.ifPresent(coordinate -> {
                allTreesTillEdgeInDirection.add(treeMap[coordinate.getRow()][coordinate.getCol()]);
                currentTree[0] = treeMap[coordinate.getRow()][coordinate.getCol()];
            } );



        } while(nextCoordinate.isPresent());

        return allTreesTillEdgeInDirection.stream().allMatch(tree -> tree.getHeight() < t.getHeight());


    };

    final static Consumer<Tree> processSingleTree = tree -> {
        directionsToSearch.forEach(direction-> {
            tree.setIsVisible(processSingleDirection.test(tree,direction));
        });
    };




    private static void createTreeMap(int[][] heightMap) {
        treeMap = new Tree[heightMap.length][];
        for (int rowNumber = 0; rowNumber < heightMap.length; rowNumber++) {
            treeMap[rowNumber] = new Tree[heightMap[rowNumber].length];
            for (int colNumber = 0; colNumber < heightMap[rowNumber].length; colNumber++) {
                var node = Tree.builder()
                        .coordinate(new Coordinate(rowNumber, colNumber))
                        .IsVisible(false)
                        .build();
                treeMap[rowNumber][colNumber] = node;
            }
        }
    }

    @Data
    @With
    @Builder
    public static class Tree implements Comparable<Tree> {
        private int height;
        private Coordinate coordinate;
        Boolean IsVisible;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tree)) {
                return false;
            }

            Tree node = (Tree) o;

            if (height != node.height) return false;
            if (IsVisible != node.IsVisible) return false;
            return coordinate.equals(node.coordinate);
        }

        @Override
        public int hashCode() {
            int result = height;
            result = 31 * result + coordinate.hashCode();
            result = 31 * result + (IsVisible ? 1 : 0);
            return result;
        }

        @Override
        public int compareTo(@NotNull TreetopTreeHouseOld.Tree o) {
            return this.height < o.getHeight() ? -1 : this.height != o.getHeight() ? 1 : 0;
        }
    }

    @Data
    @RequiredArgsConstructor
    @Builder
    public static class Coordinate implements Comparable<Coordinate> {
        private int row;
        private int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Coordinate o) {
            return (this.row - o.row) != 0 ? (this.row - o.row) : (this.col - o.col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if( !(o instanceof Coordinate)) {
                return  false;
            }

            Coordinate that = (Coordinate) o;

            if (row != that.row) return false;
            return col == that.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }

    public enum Direction {
        NORTH {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getRow() > 0) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow() - 1, thisCoordinate.getCol()));
                } else {
                    return Optional.empty();
                }
            }
        },
        EAST {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getCol() < heightMap[thisCoordinate.getRow()].length - 1) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow(), thisCoordinate.getCol() + 1));
                } else {
                    return Optional.empty();
                }
            }

        },
        SOUTH {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getRow() < heightMap.length - 1) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow() + 1, thisCoordinate.getCol()));
                } else {
                    return Optional.empty();
                }
            }
        },
        WEST {
            @Override
            public Optional<Coordinate> findNeighbour(Coordinate thisCoordinate) {
                if (thisCoordinate.getCol() > 0) {
                    return Optional.of(new Coordinate(thisCoordinate.getRow(), thisCoordinate.getCol() - 1));
                } else {
                    return Optional.empty();
                }
            }
        };

        public abstract Optional<Coordinate> findNeighbour(Coordinate startCoordinate);
    }
}
