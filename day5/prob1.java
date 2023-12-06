package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class prob1 {
    public static void main(String[] args) throws IOException {
    
        FileReader fr = new FileReader(new java.io.File("day5/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        long min = Long.MAX_VALUE;
        String line;

        line = br.readLine();

        List<Long> seeds = List.of(line.split(":")[1].strip().split(" ")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());

        Map<Integer, List<List<Long>>> mappings = new HashMap<>();
        int section = 0;
        while((line = br.readLine()) != null) {
            if(line.equals("")) {

            } else if(line.contains(":")) {
                section++;
            } else {
                List<List<Long>> mapping = mappings.getOrDefault(section, new ArrayList<>());
                mapping.add(List.of(line.split(" ")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList()));
                mappings.put(section, mapping);
            }
        }

        Map<Integer, Boolean> hasBeenMapped = new HashMap<>();
        
        for (List<List<Long>> currentMapping : mappings.values()) {    
            resetHasBeenMapped(hasBeenMapped, seeds);    
            for(List<Long> currentMap : currentMapping) {
                long size = currentMap.get(2);
                long sourceStart = currentMap.get(1);
                long destinationStart = currentMap.get(0);
                long offset = destinationStart - sourceStart;

                for(int i=0; i<seeds.size(); i++) {

                    long seed = seeds.get(i);
                
                    if(!hasBeenMapped.get(i) && seed >= sourceStart && seed < sourceStart + size) {
                        seeds.set(i, seed+offset);
                        hasBeenMapped.put(i, true);
                    }
                }

            }
        }

        min = seeds.stream().min(Comparator.comparing(Long::longValue)).get();

        System.out.println(min);
    }

    private static void resetHasBeenMapped(Map<Integer, Boolean> hasBeenMapped, List<Long> seeds) {
        for (int i=0; i<seeds.size(); i++) {
            hasBeenMapped.put(i, false);
        }
    }
}
