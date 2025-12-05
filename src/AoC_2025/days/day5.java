package AoC_2025.days;

import AoC_2025.io.Buffer;

import java.util.ArrayList;
import java.util.HashSet;

public class day5 {
    public static void main(String[] args) {
//        var file = "day5_ex.txt";
        var file = "day5.txt";
        part1(file);
//        part2(file);
    }

    private static void part1(String file) {
        var items = new HashSet<Long>();
        var minMaxList = new ArrayList<Long[]>();
        getItems(file, items);
        buildMinMaxList(file, minMaxList);

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
        System.out.println("total : " + total);
    }

    private static void buildMinMaxList(String file, ArrayList<Long[]> minMaxList) {
        try(var reader = Buffer.reader(file)) {
            var line = reader.readLine();
            while (line != null){
                if(line.isEmpty()){
                    line = null;
                } else {
                    var minMax = line.split("-");
                    var min = Long.parseLong(minMax[0]);
                    var max = Long.parseLong(minMax[1]);
                    minMaxList.add(new Long[]{min,max});
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
