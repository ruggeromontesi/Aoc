package it.ruggero.adventofcode2021.day15.part2;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public class MatrixMapping {

    public static int[][] extendMatrix(int[][] in) {
        int heigth = in.length;
        int width = in[0].length;
        if (Arrays.stream(in).mapToInt(row -> row.length).anyMatch(i -> i != width)) {
            throw new RuntimeException("rows are having different length!");
        }

        int outHeigth = 5 * heigth;
        int outWidth = 5 * width;
        int[][] out = new int[outHeigth][outWidth];

        IntBinaryOperator increase = (i,j) -> i/heigth + j /width;

        IntBinaryOperator mapper = (n,incr) ->(n+incr < 10)? n+incr : (n + incr)%9;

        for( int i = 0; i < outHeigth; i++) {
            for(int j = 0; j < outWidth; j++) {
                //out[i][j] = convert.applyAsInt(in[i%heigth][j%width],) //convert.applyAsInt(in[i%heigth][j%width]);

                out[i][j] = mapper.applyAsInt(in[i%heigth][j%width], increase.applyAsInt(i,j));
            }
        }

        return out;
    }


}
