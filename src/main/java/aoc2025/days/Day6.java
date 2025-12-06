package aoc2025.days;

import aoc2025.io.Buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class Day6 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day6.class);
    private static final Pattern  SPACES = Pattern.compile(" +");

    public static void main(String[] args) {
//        var file = "day6_ex.txt";
        var file = "day6.txt";
//        part1(file);
        part2(file);
    }

    private static void part2(String file) {
        try(var reader = Buffer.reader(file)) {

            var lines = reader.lines().toArray(String[]::new);
            var operators = SPACES.split(lines[lines.length - 1]);
            var counterOperator = operators.length - 1;
            var length = lines[0].length();
            long total = 0;
            long totalLine = 0;
            for (var i = length-1; i >= 0; i--) {
                var number = new StringBuilder(String.valueOf(lines[0].charAt(i)));
                for (var j = 1; j < lines.length-1; j++) {
                    number.append(lines[j].charAt(i));
                }
                if(number.toString().isBlank()){
                    total += totalLine;
//                    LOGGER.info("totalLine : {}", totalLine);
                    totalLine = 0;
                    counterOperator--;
                    continue;
                }
//                number = number.replace(' ', '0');
                if(totalLine != 0){
                    totalLine = compute(totalLine, number.toString().trim(), operators[counterOperator]);
                } else {
                    totalLine = Long.parseLong(number.toString().trim());
                }

//                LOGGER.info(number);
            }
//            LOGGER.info("totalLine : {}", totalLine);
            total += totalLine;
            LOGGER.info("total : {}", total);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static void part1(String file) {
        try(var reader = Buffer.reader(file)) {

            var lines = reader.lines()
                    .map(line -> SPACES.split(line.trim()))
                    .toArray(String[][]::new);
            long total = 0;

            for (var i = 0; i < lines[0].length; i++) {
                var totalLine = Long.parseLong(lines[0][i]);
                for (var j = 1; j < lines.length-1; j++) {
                    totalLine = compute(totalLine, lines[j][i], lines[lines.length-1][i]);
                }
                total += totalLine;
//                LOGGER.info("totalLine : " + totalLine);
            }
            LOGGER.info("total: {}", total);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
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
