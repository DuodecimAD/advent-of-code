package aoc2025.days;

import aoc2025.io.Buffer;
import java.io.IOException;

public class Day2 {

    public static void main(String[] args) {
        var ex = "day2_ex.txt";
        var full = "day2.txt";

//        part1(full);
        part2(full);
    }

    private static long lookForRepeats(long i, long counter) {
        var iString = String.valueOf(i);
        var length = iString.length()/2;
        for (int j = 1; j <= length; j++) {

            if(iString.substring(j).matches("(" + iString.substring(0,j) + ")+")){
//                System.out.println(i);
                counter += i;
                return counter;
            }
        }
        return counter;
    }

    private static void part2(String path) {
        long startTime = System.nanoTime();
        try(var reader = Buffer.reader(path)){
            var line = reader.readLine();
            long counter = 0;

            while(line != null){
                var items = line.split(",");
                for(var item : items){
                    var minMax = item.split("-");
                    var min = Long.parseLong(minMax[0]);
                    var max = Long.parseLong(minMax[1]);

                    for(long i = min; i <= max; i++){
                        counter = lookForRepeats(i, counter);
                    }
                }
                System.out.println(counter);
                line = reader.readLine();
            }

        } catch (IOException ioe) {
            System.out.println( "Error reading file: " + ioe);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
    }

    private static void part1(String path) {
        long startTime = System.nanoTime();
        try(var reader = Buffer.reader(path)){
            var line = reader.readLine();
            long counter = 0;

            while(line != null){
                var items = line.split(",");
                for(var item : items){
                    var minMax = item.split("-");
                    var min = Long.parseLong(minMax[0]);
                    var max = Long.parseLong(minMax[1]);

                    for(long i = min; i <= max; i++){
                        var iString = String.valueOf(i);
                        if(iString.substring(0,iString.length()/2).equals(iString.substring(iString.length()/2))){
//                            System.out.println(i);
                            counter+=i;
                        }
                    }
                }
                System.out.println(counter);
                line = reader.readLine();
            }

        } catch (IOException ioe) {
            System.out.println( "Error reading file: " + ioe);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
    }
}
