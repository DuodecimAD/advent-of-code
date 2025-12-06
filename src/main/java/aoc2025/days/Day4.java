package aoc2025.days;

import aoc2025.io.Buffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day4 {

    private static final List<int[]> NEARBY = List.of(new int[]{0, 0}, new int[]{-1, 0}, new int[]{1, 0},
                                                    new int[]{0, -1}, new int[]{-1, -1}, new int[]{-1, 1},
                                                    new int[]{0, 1}, new int[]{1, -1}, new int[]{1, 1});

    public static void main(String[] args) {
//        var file = "day4_ex.txt";
        var file = "day4.txt";
//        part1(file);
        part2(file);
    }

    private static int getTotal(HashSet<String> set, boolean bool) {
        if(!bool){
            return 0;
        }
        List<String> toRemove = new ArrayList<>();
        var total = 0;
        for (var loc : set) {
            var locToCheck = loc.split("@");
            var row = Integer.parseInt(locToCheck[0]);
            var column = Integer.parseInt(locToCheck[1]);
            var count = 0;
            for (var nearby : NEARBY) {
                var rowToCheck = row + nearby[0];
                var columnToCheck = column + nearby[1];
                if (set.contains(rowToCheck+"@"+columnToCheck)){
                    count++;
                }
            }
            if(count <= 4){
                total++;
                toRemove.add(loc);
            }
        }
        if(total == 0){
            bool = false;
        }
//        System.out.println(total);
        set.removeAll(toRemove);
        return total + getTotal(set, bool);
    }

    private static void part2(String file) {
        try(var reader = Buffer.reader(file)) {
            var set = new HashSet<String>();
            buildSet(reader, set);
            System.out.println(getTotal(set, true));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void part1(String file) {
        try(var reader = Buffer.reader(file)) {
            var set = new HashSet<String>();
            buildSet(reader, set);
            System.out.println(getTotal(set));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void buildSet(BufferedReader reader, HashSet<String> set) throws IOException {
        var line = reader.readLine();
        var lineCounter = 0;
        var length = line.length();
        while (line != null){
            for (int i = 0; i < length; i++) {
                if (line.charAt(i) == '@'){
                    set.add(lineCounter+"@"+i);
                }
            }
            lineCounter++;
            line = reader.readLine();
        }
    }

    private static int getTotal(HashSet<String> map) {
        var total = 0;
        for (var loc : map) {
            var locToCheck = loc.split("@");
            var row = Integer.parseInt(locToCheck[0]);
            var column = Integer.parseInt(locToCheck[1]);
            var count = 0;
            for (var nearby : NEARBY) {
                var rowToCheck = row + nearby[0];
                var columnToCheck = column + nearby[1];
                if (map.contains(rowToCheck+"@"+columnToCheck)){
                    count++;
                }
            }
            if(count <= 4){
                total++;
            }
        }
        return total;
    }
}
