package it.ruggero.util.core;

import it.ruggero.adventofcode2022.day8.Tree;
import it.ruggero.util.input.FilePathResolver;
import it.ruggero.util.input.FileToInteger2DArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class AocMapTest {
    private static AocMap<Tree> entity2DArray;

    @BeforeAll
    static void atBeginning() {
        var matrix = new FileToInteger2DArray(new FilePathResolver(8, 2022)).readSample();
        BiFunction<AocMap.Coordinate, Integer, Tree> mapper = (AocMap.Coordinate c, Integer h) ->
                Tree.builder().coordinate(c).height(h).build();
        entity2DArray = new AocMap<>(matrix, Tree.class, mapper);
    }

    @Test
    void shouldFindNorthNeighbour() {
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(1).col(0).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.NORTH).orElseThrow();
        assertThat(result).isEqualTo(AocMap.Coordinate.builder().row(0).col(0).build());
    }

    @Test
    void shouldNotFindNorthNeighbour() {
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(0).col(0).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.NORTH);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindEastNeighbour() {
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(0).col(0).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.EAST).orElseThrow();
        assertThat(result).isEqualTo(AocMap.Coordinate.builder().row(0).col(1).build());
    }

    @Test
    void shouldNotFindEastNeighbour() {
        int maxColumnIndex = entity2DArray.getSourceInputMap()[0].length - 1;
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(0).col(maxColumnIndex).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.EAST);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindSouthNeighbour() {
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(0).col(0).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.SOUTH).orElseThrow();
        assertThat(result).isEqualTo(AocMap.Coordinate.builder().row(1).col(0).build());
    }

    @Test
    void shouldNotFindSouthNeighbour() {
        int maxRowIndex = entity2DArray.getSourceInputMap().length - 1;
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(maxRowIndex).col(0).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.SOUTH);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindWestNeighbour() {
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(0).col(1).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.WEST).orElseThrow();
        assertThat(result).isEqualTo(AocMap.Coordinate.builder().row(0).col(0).build());
    }

    @Test
    void shouldNotFindWestNeighbour() {
        AocMap.Coordinate coordinate = AocMap.Coordinate.builder().row(0).col(0).build();
        var result = entity2DArray.findNeighbourOfCoordinateInDirection(coordinate, Directions.WEST);
        assertThat(result).isEmpty();
    }

    @Test
    void shouldCheckIfCoordinateIsOnTheBorder() {
        var coordinates = List.of(
                AocMap.Coordinate.builder().row(0).col(0).build(),
                AocMap.Coordinate.builder().row(0).col(entity2DArray.getSourceInputMap()[0].length - 1).build(),
                AocMap.Coordinate.builder().row(entity2DArray.getSourceInputMap().length - 1).col(0).build(),
                AocMap.Coordinate.builder().row(entity2DArray.getSourceInputMap().length-1).col(entity2DArray.getSourceInputMap()[entity2DArray.getSourceInputMap().length-1].length - 1).build()
        );
        var result = coordinates.stream().allMatch(entity2DArray::isOnTheBorder);
        assertThat(result).isTrue();
    }


    @Test
    void shouldNotBeOnTheBorder() {
        AocMap.Coordinate coordinate1 = AocMap.Coordinate.builder().row(1).col(1).build();
        var result = entity2DArray.isOnTheBorder(coordinate1);
        assertThat(result).isFalse();

    }
}
