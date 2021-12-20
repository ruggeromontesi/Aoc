
package it.ruggero.adventofcode2021.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    private static  final String FILE_PATH_ACTUAL = ".\\src\\main\\resources\\day8\\day8.txt";
    private static final String FILE_PATH_TEST = ".\\src\\main\\resources\\day8\\testDay8.txt";

    public static void main(String[] args) {
        String filePath = FILE_PATH_ACTUAL;
        readFile(filePath);
    }



    public static void secondPart() {
        String filePath = FILE_PATH_ACTUAL;
        String line;
        int sum = 0;

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();
                sum += createMapping(line);
                System.out.println(" sum " + sum);
            }

        } catch(FileNotFoundException ex) {
            System.out.println( ex );
        }
        System.out.println(" sum " + sum);
    }



    public static void readFile(String filePath) {
        String line;
        int counter = 0;

        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                line = input.nextLine();
                String[] digits = line.substring(line.indexOf('|')).trim().split(" ");
                for(String string : digits) {
                    if (string.length() == 2 || string.length() == 3 || string.length() == 4 || string.length() == 7) {
                        counter++;
                    }
                }
            }

        } catch(FileNotFoundException ex) {
            System.out.println( ex );
        }
        System.out.println(" counter " + counter);
        System.out.println("map " + DisplayMapping.getDigits());
        //DisplayMapping.getDigits().stream().forEach(System.out::println);
    }


    public static int convert(String[] inputLine) {
        if(inputLine.length != 4) {
            throw new RuntimeException("input string is not 4 digits!");
        }
        int output = 0;
        int digitIndex =3;
        Map<Integer, Set<Character>> map = DisplayMapping.getDigits();
        Set<Character> inputSet = new TreeSet<>();
        for(String input : inputLine) {

            for(int i = 0; i < input.length(); i++) {
                inputSet.add(input.charAt(i));

            }
            if( map.containsValue(inputSet)) {
                System.out.println("ok!!!!");

                for(Integer index : map.keySet() ) {
                    if( map.get(index).equals(inputSet)) {
                        //return index;
                        output += Math.pow(10, digitIndex--)*index;
                    }
                }
            }
        }

        return output;
    }


    public static int createMapping(String inputString) {
        Map<TreeSet<Character>, Integer> map = new HashMap<>();
        String[] digits = inputString.substring(0, inputString.indexOf('|')).trim().split(" ");
        Set<Character> inputSet = new TreeSet<>();
        TreeSet<Character> zero;
        TreeSet<Character> one =  new TreeSet<>();
        TreeSet<Character> two;
        TreeSet<Character> three = new TreeSet<>();
        TreeSet<Character> four =  new TreeSet<>();
        TreeSet<Character> five = new TreeSet<>();
        TreeSet<Character> six = new TreeSet<>();
        TreeSet<Character> seven;
        TreeSet<Character> eight;
        TreeSet<Character> nine = new TreeSet<>();

        List<TreeSet<Character> > listFiveSegments = new ArrayList<>();
        List<TreeSet<Character> > listSixSegments = new ArrayList<>();

        for(String digit : digits) {
            if(digit.length() == 2) {
                one = stringToTree(digit);
                map.put(one,1);
                continue;
            }

            if(digit.length() == 3) {
                seven = stringToTree(digit);
                map.put(seven,7);
                continue;
            }

            if(digit.length() == 4) {
                four = stringToTree(digit);
                map.put(four,4);
                continue;
            }


            if(digit.length() == 7) {
                eight = stringToTree(digit);
                map.put(eight,8);
                continue;
            }

            if(digit.length() == 5) {
                listFiveSegments.add(stringToTree(digit));
                continue;
            }

            if(digit.length() == 6) {
                listSixSegments.add(stringToTree(digit));
                continue;
            }

        }


        //il digit a 5 segmenti che contiene 1 e' 3
        outer: for(TreeSet<Character> digit : listFiveSegments) {

            for(Character character : one) {
                if( !digit.contains(character)) {
                    continue outer;
                }
            }
            three = digit;
            map.put(three,3);
            break;
        }
        listFiveSegments.remove(three);

        //il digit a 5 segmenti che NON contiene 1 e' 6
        for(TreeSet<Character> digit : listSixSegments) {
            boolean contains1 = true;
            for(Character character : one) {
                if( !digit.contains(character)) {
                    contains1 = false;
                }
            }
            if(!contains1) {
                six = digit;
                map.put(six,6);
                break;
            }

        }
        listSixSegments.remove(six);



        //il digit a 5 segmenti che e' contenuto in 6  e' 5
        outer: for(TreeSet<Character> digit : listFiveSegments) {
            for(Character character : digit ) {
                if( !six.contains(character)) {
                    continue outer;
                }
            }
            five = digit;
            map.put(five,5);
            break;

        }

        listFiveSegments.remove(five);

        if (listFiveSegments.size() != 1) {
            throw  new RuntimeException("error! athis point listFiveSegments should have contained only one element");
        } else {
            two = listFiveSegments.get(0);
            map.put(two,2);
        }

        //il digit a 6 segmenti che contiene 4   e' 9
        outer: for(TreeSet<Character> digit : listSixSegments) {

            for(Character character : four) {
                if( !digit.contains(character)) {
                    continue outer;
                }
            }
            nine = digit;
            map.put(nine,9);
            break;
        }
        listSixSegments.remove(nine);
        if (listSixSegments.size() != 1) {
            throw  new RuntimeException("error! at this point listSixSegments should have contained only one element");
        } else {
            zero = listSixSegments.get(0);
            map.put(zero,0);
        }

        String[] values = inputString.substring(inputString.indexOf('|')+1).trim().split((" "));

        int output = 0;
        int i =3;
        for(String value : values) {
            output +=(int) map.get(stringToTree(value))* (int) Math.pow(10, i--);

        }


        System.out.println("output " + output);
        return output;
    }


    public static TreeSet<Character> stringToTree(String input) {
        TreeSet<Character> output = new TreeSet<>();
        for(int i =0; i < input.length(); i++) {
            output.add(input.charAt(i));
        }
        return output;
    }



}
