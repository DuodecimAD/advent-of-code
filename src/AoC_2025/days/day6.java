package AoC_2025.days;

import AoC_2025.io.Buffer;

public class day6 {
    public static void main(String[] args) {
//        var file = "day6_ex.txt";
        var file = "day6.txt";
//        part1(file);
        part2(file);
    }

    private static void part2(String file) {
        try(var reader = Buffer.reader(file)) {

            var lines = reader.lines().toArray(String[]::new);
            var operators = lines[lines.length-1].split(" +");
            var counterOperator = operators.length - 1;
            var length = lines[0].length();
            long total = 0;
            long totalLine = 0;
            for (int i = length-1; i >= 0; i--) {
                String number = String.valueOf(lines[0].charAt(i));
                for (int j = 1; j < lines.length-1; j++) {
                    number += lines[j].charAt(i);
                }
                if(number.isBlank()){
                    total += totalLine;
//                    System.out.println("totalLine : " + totalLine);
                    totalLine = 0;
                    counterOperator--;
                    continue;
                }
//                number = number.replace(' ', '0');
                if(totalLine != 0){
                    totalLine = compute(totalLine, number.trim(), operators[counterOperator]);
                } else {
                    totalLine = Long.parseLong(number.trim());
                }

//                System.out.println(number);
            }
//            System.out.println("totalLine : " + totalLine);
            total += totalLine;
            System.out.println("total : " + total);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void part1(String file) {
        try(var reader = Buffer.reader(file)) {

            var lines = reader.lines()
                    .map(line -> line.trim().split(" +"))
                    .toArray(String[][]::new);
            long total = 0;

            for (int i = 0; i < lines[0].length; i++) {
                long totalLine = Long.parseLong(lines[0][i]);
                for (int j = 1; j < lines.length-1; j++) {
                    totalLine = compute(totalLine, lines[j][i], lines[lines.length-1][i]);
                }
                total += totalLine;
                System.out.println("totalLine : " + totalLine);
            }
            System.out.println("total: " + total);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static long compute(long totalLone, String numberS, String operator) {

        var number = Integer.parseInt(numberS);
        return switch (operator) {
            case "+" -> totalLone + number;
            case "*" -> totalLone * number;
            case "-" -> totalLone - number;
            case "/" -> totalLone / number;
            default -> throw new RuntimeException("Unknown operator : " + operator);
        };
    }
}
