package AoC_2025.days;

import AoC_2025.io.Buffer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class day5 {
    public static void main(String[] args) {
//        var file = "day5_ex.txt";
        var file = "day5.txt";
        part1(file);
//        part2(file);
    }

    private static void part1(String file) {
        var freshListLine = new HashSet<Long>();
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
//                    var minMax = line.split("-");
//                    var min = Long.parseLong(minMax[0]);
//                    var max = Long.parseLong(minMax[1]);
//                    for (long i = min; i <= max; i++) {
//                        freshListLine.add(String.valueOf(i));
//                    }

                    freshListLine.add(Long.parseLong(line));
                }

//                System.out.println(line);
                line = reader.readLine();

            }

//            System.out.println(total);
//            for (var item : freshListLine){
//                System.out.println(item);
//            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try(var reader = Buffer.reader(file)) {
            var line = reader.readLine();
            int total = 0;
            while (line != null){
                if(line.length() == 0){
                    return;
                }
                System.out.println(line);
                var minMax = line.split("-");
                var min = Long.parseLong(minMax[0]);
                var max = Long.parseLong(minMax[1]);

                for (var value : freshListLine) {
                    if (value >= min && value <= max) {
                        System.out.println(value + "yes");
                        freshListLine.remove(value);
                        total++;
                    }
                }

                line = reader.readLine();
            }
            System.out.println(total);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
