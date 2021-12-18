package it.ruggero.adventofcode2021.day7;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.IntStream;


public class DaySeven {
    private static final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day7\\positions.txt";
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day7\\testPositions.txt";

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("positions.txt")) {
            if(input != null ) {
                //goSimple(input);
                go(input);

            } else {
                System.out.println("input is null!");
            }

        }
    }


    public static void goSimple(InputStream input) {
        int[] hCoordinatesArray =  readFile(input);
        int[] candidatePositionsArray = IntStream.rangeClosed(IntStream.of(hCoordinatesArray).min().orElseThrow(), IntStream.of(hCoordinatesArray).max().orElseThrow()).toArray();
        int min = IntStream.rangeClosed(IntStream.of(hCoordinatesArray).min().orElseThrow(), IntStream.of(hCoordinatesArray)
                .max().orElseThrow()).map( x -> IntStream.of(hCoordinatesArray).map(y ->Math.abs(y-x)).sum()).min().orElseThrow();
        min = IntStream.of(candidatePositionsArray).map( x -> IntStream.of(hCoordinatesArray).map(y ->Math.abs(y-x)).sum()).min().orElseThrow();
        System.out.println("minimum fuel power consumption is " + min);
    }

    public static void go(InputStream input) throws IOException{
        int[] hCoordinatesArray =  readFile(input);
        int[] candidatePositionsArray = IntStream.rangeClosed(IntStream.of(hCoordinatesArray).min().orElseThrow(), IntStream.of(hCoordinatesArray).max().orElseThrow()).toArray();


        int min2 =          IntStream.of(candidatePositionsArray).map(
                x -> IntStream.of(hCoordinatesArray).map(y -> calculateConsumption(x,y)).sum()
        ).min().orElseThrow();
        System.out.println("minimum fuel power consumption is " + min2);
        //minimum fuel power consumption is 95167302

/*        IntStream.of(candidatePositionsArray).map(
                x -> IntStream.of(hCoordinatesArray).map(y -> calculateConsumption(x,y)).sum()
        ).forEach(System.out::println);*/


    }


    public static int  calculateConsumption(int start, int stop) {
        int length = Math.abs(stop-start)+1;
        return  IntStream.iterate(0, x ->x+1 ).limit(length).sum();
    }


    public static int[] readFile(InputStream input  )   {
        String readData = "";


            if(input == null) {
                System.out.println("input is null!");
            }
            return new Scanner(input).useDelimiter(",").tokens().mapToInt(Integer::valueOf).toArray();


/*        try(Scanner scanner = new Scanner(new File(filePath)) ) {
            while(scanner.hasNextLine()){
                readData += scanner.nextLine();
            }

        } catch (FileNotFoundException ex) {

        }*/


    }




/*        //IntStream hCoordinates = IntStream.of(16,1,2,0,4,2,7,1,2,14);
        int[] hCoordinatesArray = readFile();
        int[] candidatePositionsArray = IntStream.rangeClosed(IntStream.of(hCoordinatesArray).min().orElseThrow(), IntStream.of(hCoordinatesArray).max().orElseThrow()).toArray();
        //IntStream candidatePositions = IntStream.rangeClosed(hCoordinates.min().orElseThrow(), hCoordinates.max().orElseThrow());
        //IntStream fuelConsumptions = candidatePositions.map( x -> x+1000);
        //fuelConsumptions  ;
       // IntStream.rangeClosed(IntStream.of(16,1,2,0,4,2,7,1,2,14).min().orElseThrow(), IntStream.of(16,1,2,0,4,2,7,1,2,14).max().orElseThrow()).map( x -> x+1000).forEach(System.out::println);



        IntStream.rangeClosed(IntStream.of(hCoordinatesArray).min().orElseThrow(), IntStream.of(hCoordinatesArray)
                .max().orElseThrow()).map( x -> IntStream.of(hCoordinatesArray).map(y ->Math.abs(y-x)).sum()).forEach(System.out::println);


        int min = IntStream.rangeClosed(IntStream.of(hCoordinatesArray).min().orElseThrow(), IntStream.of(hCoordinatesArray)
                .max().orElseThrow()).map( x -> IntStream.of(hCoordinatesArray).map(y ->Math.abs(y-x)).sum()).min().orElseThrow();
        System.out.println("minimum fuel power consumption is " + min);*/

}

