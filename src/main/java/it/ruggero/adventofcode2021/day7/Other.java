package it.ruggero.adventofcode2021.day7;

import java.util.stream.IntStream;


public class Other {

    public static void main(String[] args) {
        int start = 0;
        int stop = 5;
        int low = Math.min(start,stop);
        int high = Math.max(start,stop);
        IntStream allPositions  = IntStream.rangeClosed(low,high);
        //allPositions.forEach(System.out::println);

        allPositions = IntStream.iterate(start, x -> ( x > low - 1 && x < high +1 ) , x -> (  start < stop)  ? (x +1) : (x-1) );
        //allPositions.forEach(System.out::println);
        allPositions = IntStream.iterate(start, x -> ( x > low - 1 && x < high +1 ) , x -> (  start < stop)  ? (x +1) : (x-1) );
        IntStream additionalConsumption = IntStream.iterate(0, x ->x+1 ).limit(allPositions.count());
        int length = Math.abs(stop-start)+1;
        IntStream.iterate(0, x ->x+1 ).limit(length).forEach(System.out::println);
        System.out.println("sum " + IntStream.iterate(0, x ->x+1 ).limit(length).sum());


        //int min = IntStream.rangeClosed(IntStream.of(hCoordinatesArray).min().orElseThrow(), IntStream.of(hCoordinatesArray)
          //      .max().orElseThrow()).map( x -> IntStream.of(hCoordinatesArray).map(y ->Math.abs(y-x)).sum()).min().orElseThrow();

        /*IntStream stream  = IntStream.rangeClosed(1,10);
        int target = 7;
        stream.map( x -> Math.abs(x-7 ) ).forEach(System.out::println);
        stream.map( x -> Math.abs(x-7 ) ).map( x -> x + 1).forEach(System.out::println);*/
    }


}

class CodeToRun implements Runnable {

    @Override
    public void run() {
        System.out.println("ruggero");
    }
}
