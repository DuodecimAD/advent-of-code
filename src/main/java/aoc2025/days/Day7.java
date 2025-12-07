package aoc2025.days;

import aoc2025.io.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;


public class Day7 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day7.class);

    public static void main(String[] args) {
        var file = "day7_ex.txt";
//        var file = "day7.txt";
//        part1(file);
        part2(file);
    }

    private static void part2(String file) {
        try (var reader = Buffer.reader(file)) {
            var line = reader.readLine();
            var total = 0;
            var lineCounter = 1;
            line = reader.readLine();
            while (line != null){
                lineCounter++;
                if(lineCounter % 2 == 0){
                    line = reader.readLine();
                    continue;
                }

                // :'(

                line = reader.readLine();
            }
            LOGGER.info("total : {}", total);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static void part1(String file) {
        try (var reader = Buffer.reader(file)) {
            var line = reader.readLine();
            var total = 0;
            var lineCounter = 1;
            var previousLineSet = new HashSet<Integer>();
            previousLineSet.add(line.indexOf("S"));
            line = reader.readLine();
            while (line != null){
                lineCounter++;
                if(lineCounter % 2 == 0){
                    line = reader.readLine();
                    continue;
                }
                LOGGER.info("{} - nb ^ : {}", lineCounter, previousLineSet);
                var currentLineSet = new HashSet<Integer>();

                for (int i = 0; i < line.length(); i++) {

                    if(line.charAt(i) == '^'){
                        if(previousLineSet.contains(i)){
                            total++;
                            previousLineSet.remove(i);
                            currentLineSet.add(i-1);
                            currentLineSet.add(i+1);
                        }
                    }
                }
                previousLineSet.addAll(currentLineSet);
                line = reader.readLine();
            }
            LOGGER.info("total : {}", total);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
