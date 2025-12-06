package aoc2025.days;

import aoc2025.io.Buffer;
import java.io.IOException;

public class Day1 {
    public static void main(String[] args) {
        var file = "day1.txt";

        System.out.println("\ndoesn't count, 5x slower because running first,waking up or something else ...");
        part2(file);
        System.out.println("*********************************");

        System.out.println("\nfirst version");
        part2(file);
        System.out.println("\nrefactoring with switch");
        part2_refactoring_withSwitch(file);
        System.out.println("\nrefactoring with if instead of switch");
        part2_refactoring(file);
    }

    private static void part2_refactoring(String path) {
        long startTime = System.nanoTime();
        try(var reader = Buffer.reader(path)){
            int counter = 0, min = 0, max = 100, pos = 50;
            var line = reader.readLine();
            while(line != null){
                var direction = line.substring(0,1).charAt(0);
                var number = Integer.parseInt(line.substring(1));
                counter += number / max;
                var rotationValue = number % max;

                if(direction == 'L'){
                    var result = pos - rotationValue;
                    if(result < min){
                        if(pos != 0) {
                            counter++;
                        }
                        pos = (max + result) % max;
                    } else {
                        pos = result % max;
                    }
                } else if(direction == 'R'){
                    var result = pos + rotationValue;
                    if(result > max){
                        counter++;
                    }
                    pos = result % max;
                }

                if(pos == 0){
                    counter++;
                }
//                System.out.printf("%4s, newPos : %4d -> 0 : %d\n", line, pos, counter);
                line = reader.readLine();
            }
            System.out.println("result : " + counter);
        } catch (IOException ioe) {
            System.out.println( "Error reading file: " + ioe);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
    }

    private static void part2_refactoring_withSwitch(String path) {
        long startTime = System.nanoTime();
        try(var reader = Buffer.reader(path)){
            int counter = 0, min = 0, max = 100, pos = 50;
            var line = reader.readLine();
            while(line != null){
                var direction = line.substring(0,1).charAt(0);
                var number = Integer.parseInt(line.substring(1));
                counter += number / max;
                var rotationValue = number % max;
                switch (direction) {
                    case 'L' -> {
                        var result = pos - rotationValue;
                        if(result < min){
                            if(pos != 0) {
                                counter++;
                            }
                            pos = (max + result) % max;
                        } else {
                            pos = result % max;
                        }
                    }
                    case 'R' -> {
                        var result = pos + rotationValue;
                        if(result > max){
                            counter++;
                        }
                        pos = result % max;
                    }
                    default ->
                        System.out.println("wtf");
                }
                if(pos == 0){
                    counter++;
                }
//                System.out.printf("%4s, newPos : %4d -> 0 : %d\n", line, pos, counter);
                line = reader.readLine();
            }
            System.out.println("result : " + counter);
        } catch (IOException ioe) {
            System.out.println( "Error reading file: " + ioe);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
    }

    private static void part2(String path) {
        long startTime = System.nanoTime();
        var dial = new int[100];

        try(var reader = Buffer.reader(path)){
            var line = reader.readLine();
            var pos = 50;
            while(line != null){
                var direction = line.substring(0,1).charAt(0);
                var number = Integer.parseInt(line.substring(1));
                dial[0] += number / dial.length;
                var rotationNumber = number % dial.length;
                if(direction == 'L'){
                    var result = pos - rotationNumber;
                    if(result < 0){
                        if(pos != 0){
                            dial[0]++;
                        }
                        pos = (dial.length + result) % dial.length;
                    } else {
                        pos = result % dial.length;
                    }
                } else {
                    var result = pos + rotationNumber;
                    if(result > dial.length){
                        dial[0]++;
                    }
                    pos = result % dial.length;
                }

                dial[pos]++;
//                System.out.printf("%4s, newPos : %4d -> 0 : %d\n", line, pos, dial[0]);
                line = reader.readLine();
            }

            System.out.println("result : " + dial[0]);

        } catch (IOException ioe) {
            System.out.println( "Error reading file: " + ioe);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
    }
}
