package it.ruggero.adventofcode2022.day8;

import it.ruggero.util.core.AocMap;
import it.ruggero.util.core.Directions;
import it.ruggero.util.input.FilePathResolver;
import it.ruggero.util.input.FileToInteger2DArray;
import it.ruggero.util.solution.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class TreetopTreeHouse implements Solution<Long> {
    private int[][] heightMap;
    private final FilePathResolver filePathResolver = new FilePathResolver(8,2022);

    private AocMap<Tree> aocMap;
    private Tree[][] treeMap;
    private final BiFunction<AocMap.Coordinate, Integer, Tree> mapper = (AocMap.Coordinate c, Integer h) ->
            Tree.builder().coordinate(c).height(h).build();

    public TreetopTreeHouse() {
        initialize();
    }

    private  void initialize() {
        heightMap = new FileToInteger2DArray(filePathResolver).readSample();
        createMap();

    }

    private boolean isTheTreeVisibleInDirection(Tree t, Directions direction) {
        List<Tree> allTreesTillEdgeInDirection = new ArrayList<>();
        Optional<AocMap.Coordinate> nextCoordinate;
        AocMap.Coordinate currentCoordinate = AocMap.Coordinate.builder()
                .row(t.getCoordinate().getRow())
                .col(t.getCoordinate().getCol())
                .build();
        do {
            nextCoordinate = aocMap.findNeighbourOfCoordinateInDirection(currentCoordinate, direction);

            if(nextCoordinate.isPresent()) {
                allTreesTillEdgeInDirection.add(treeMap[nextCoordinate.get().getRow()][nextCoordinate.get().getCol()]);
                currentCoordinate = AocMap.Coordinate.builder().row(nextCoordinate.get().getRow()).col(nextCoordinate.get().getCol()).build();
            }
        } while (nextCoordinate.isPresent());

        return allTreesTillEdgeInDirection.stream().allMatch(tree -> tree.getHeight() < t.getHeight());
    }

    private void calculateIfTreeIsVisible(Tree tree) {
        var visible = Stream.of(Directions.values()).anyMatch(direction -> isTheTreeVisibleInDirection(tree,direction));
        tree.setIsVisible(visible);

    }

    public void calculateWhichTreesAreVisible() {
        aocMap.stream().filter(tree -> aocMap.isOnTheBorder(tree.getCoordinate())).forEach(tree -> tree.setIsVisible(true));
        aocMap.stream().filter(tree -> !aocMap.isOnTheBorder(tree.getCoordinate())).forEach(this::calculateIfTreeIsVisible);
    }

    @Override
    public Long solvePartOneSample() {
        heightMap = new FileToInteger2DArray(filePathResolver).readSample();
        createMap();
        calculateWhichTreesAreVisible();
        return aocMap.stream().filter(Tree::getIsVisible).count();
    }

    private void createMap() {
        aocMap = new AocMap<>(heightMap, Tree.class, mapper);
        treeMap = aocMap.getEntityMap();
    }

    @Override
    public Long solvePartOne() {
        heightMap = new FileToInteger2DArray(filePathResolver).read();
        createMap();
        calculateWhichTreesAreVisible();
        return aocMap.stream().filter(Tree::getIsVisible).count();
    }

    @Override
    public Long solvePartTwoSample() {
        return null;
    }

    @Override
    public Long solvePartTwo() {
        return null;
    }
}
