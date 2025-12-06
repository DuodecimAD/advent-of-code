package AoC_2025.days;

import AoC_2025.io.Buffer;

public class day6 {
    public static void main(String[] args) {
//        var file = "day6_ex.txt";
        var file = "day6.txt";
        part1(file);
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
            System.out.println(e);
        }
    }

    private static long compute(long totalLone, String numberS, String operator) {

        var number = Integer.parseInt(numberS);
        switch (operator) {
            case "+":
                return totalLone + number;
            case "*":
                return totalLone * number;
            case "-":
                return totalLone - number;
            case "/":
                return totalLone / number;
        }
        throw new RuntimeException("Unknown operator : " + operator);
    }
}
