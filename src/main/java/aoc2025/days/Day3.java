package aoc2025.days;

import aoc2025.io.Buffer;

public class Day3 {
    public static void main(String[] args) {
//        var file = "day3_ex.txt";
        var file = "day3.txt";
//        part1(file);
        part2(file);
    }

    private static void part2(String file) {

        try(var reader = Buffer.reader(file)) {
            long total = 0;

            var line = reader.readLine();
            while (line != null){

                var number = getNumberForLine(line);

                total += number;
//                System.out.println("line number : " + number);
//                System.out.println("line total : " + total);

                line = reader.readLine();
            }
            System.out.println("total : " +total);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static long getNumberForLine(String line) {
        var numbers = "";
        var max = 0;
        var pos = 0;
        var length = line.length();
        var numberLength = 12;
        var counter = 12;
        var LastIteration = 0;
        for (int k = 0; k < length; k++) {
            LastIteration = length-counter;
            var valuei = 0;
            for(int i = pos; i <= LastIteration; i++){
                valuei = Integer.parseInt(String.valueOf(line.charAt(i)));
                if (valuei > max && i <= LastIteration){
                    max = valuei;
                    pos = i+1;
                }

                if(i == LastIteration) {
                    counter--;
                    numbers += max;
                    max = 0;
                }
            }
//            System.out.println(numbers);
            if(numbers.length() == numberLength) break;
        }
        return Long.parseLong(numbers);
    }

    private static void part1(String file) {

        try(var reader = Buffer.reader(file)) {
            var total = 0;
            var line = reader.readLine();
            var length = line.length();
            while (line != null){

                var firstMax = 0;
                var secondMax = 0;
                var pos = 0;
                var valuei = 0;

                for(int i = 0; i < length; i++){
                    valuei = Integer.parseInt(String.valueOf(line.charAt(i)));
                    if (valuei > firstMax && i < length-1){
                        firstMax = valuei;
                        pos = i+1;
                    }
                }
                for(int i = pos; i < length; i++){
                    valuei = Integer.parseInt(String.valueOf(line.charAt(i)));
                    if (valuei >= secondMax){
                        secondMax = valuei;
                    }
                }
                var number = firstMax+""+secondMax;
                total += Integer.parseInt(number);
//                System.out.println(number);
                line = reader.readLine();
            }
            System.out.println(total);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
