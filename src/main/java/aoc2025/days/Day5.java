package aoc2025.days;

import aoc2025.io.Buffer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Day5 {
    public static void main(String[] args) {
//        var file = "day5_ex.txt";
        var file = "day5.txt";
//        part1(file);
        part2Try2(file);
    }

    private static void part2Try2(String file) {
        var minMaxList = new ArrayList<long[]>();
        buildMinMaxList(file, minMaxList);
        long total = 0;
        minMaxList.sort(Comparator.comparingLong(a -> a[0]));

        var cleanedList = new ArrayList<long[]>();
        long[] current = minMaxList.getFirst();

        for (int i = 1; i < minMaxList.size(); i++) {
            long[] next = minMaxList.get(i);

            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                cleanedList.add(current);
                current = next;
            }
        }

        cleanedList.add(current);
        for (var minMax : cleanedList) {
            total += minMax[1]-minMax[0]+1;
//            System.out.println(minMax[0] + " - " + minMax[1]);
        }
        System.out.println("total : " + total);
    }

    // not working - lost in my mind's labyrinth
    private static void part2(String file) {

        var minMaxList = new ArrayList<long[]>();
        buildMinMaxList(file, minMaxList);
        var total = 0;

        var cleanedList = new HashSet<Long[]>();
        for (var line : minMaxList) {
            var toRemove = new HashSet<>();
            for (var minMax : minMaxList){
                if(minMax[0] == line[0] && minMax[1] == line[1]){
                    break;
                }
                long min = line[0];
                long max = line[1];
//                System.out.println(minMax[0] + " - " + minMax[1] + " : " + line[0] + " - " + line[1]);
                if((minMax[0] < line[0] && minMax[0] > line[1]) || (line[0] < minMax[0] && line[0] > minMax[1])){
//                    3            10          3         14           10         3            10        5
//                    16           12         16         18           12         16            12       18
                    System.out.println(minMax[0] + " - " + minMax[1] + " : " + line[0] + " - " + line[1]);
                    min = Math.min(minMax[0], line[0]);
                }

                if((minMax[1] > line[0] && minMax[1] < line[1]) || (line[1] > minMax[0] && line[1] < minMax[1])){
                    System.out.println(minMax[0] + " - " + minMax[1] + " : " + line[0] + " - " + line[1]);
                    max = Math.max(minMax[1], line[1]);
                }
                var newItem = new Long[]{min,max};
                cleanedList.add(newItem);
            }
        }

        for (var line : cleanedList) {
//            total += line[1]-line[0];
            System.out.println(line[0] + " - " + line[1]);
            System.out.println(line[1]-line[0]);
        }

        System.out.println("total : " + total);
    }

    private static void part1(String file) {
        var items = new HashSet<Long>();
        var minMaxList = new ArrayList<long[]>();
        getItems(file, items);
        buildMinMaxList(file, minMaxList);
        var total = getTotalFreshItem(minMaxList, items);
        System.out.println("total : " + total);
    }

    private static int getTotalFreshItem(ArrayList<long[]> minMaxList, HashSet<Long> items) {
        var total = 0;
        for (var minMax : minMaxList) {
            var itemsConcurentModificationCopy = new HashSet<>(items);
            for (var item : itemsConcurentModificationCopy) {
                if(item >= minMax[0] && item <= minMax[1]){
                    total++;
                    items.remove(item);
                }
            }
        }
        return total;
    }

    private static void buildMinMaxList(String file, ArrayList<long[]> minMaxList) {
        try(var reader = Buffer.reader(file)) {
            var line = reader.readLine();
            while (line != null){
                if(line.isEmpty()){
                    line = null;
                } else {
                    var minMax = line.split("-");
                    var min = Long.parseLong(minMax[0]);
                    var max = Long.parseLong(minMax[1]);
                    minMaxList.add(new long[]{min,max});
                    line = reader.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void getItems(String file, HashSet<Long> freshListLine) {
        try(var reader = Buffer.reader(file)) {
            var line = reader.readLine();
            boolean items = false;

            while (line != null){
                if(line.length() == 0){
                    items = true;
                    line = reader.readLine();
                    continue;
                }
                if(items){
                    freshListLine.add(Long.parseLong(line));
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
