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

public class prob2 {
    public static void main(String[] args) throws IOException {
    
        FileReader fr = new FileReader(new java.io.File("day5/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        long min = Long.MAX_VALUE;
        String line;

        line = br.readLine();

        List<Long> firstLine = List.of(line.split(":")[1].strip().split(" ")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());

        
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

        
        for(int i=0; i<firstLine.size(); i+=2) {
            for(long j=firstLine.get(i); j<firstLine.get(i)+firstLine.get(i+1); j++) {
                long currentSeed = j;

                for (List<List<Long>> currentMapping : mappings.values()) {    
                    for(List<Long> currentMap : currentMapping) {
                        long size = currentMap.get(2);
                        long sourceStart = currentMap.get(1);
                        long destinationStart = currentMap.get(0);
                        long offset = destinationStart - sourceStart;
                        if(currentSeed >= sourceStart && currentSeed < sourceStart + size) {
                            currentSeed += offset;
                            break;
                        }
                    }   
                }
                
                if(currentSeed < min) {
                    min = currentSeed;
                }
            }
        }
        
        System.out.println(min);
    }

    private static void resetHasBeenMapped(Map<Integer, Boolean> hasBeenMapped, List<Long> seeds) {
        for (int i=0; i<seeds.size(); i++) {
            hasBeenMapped.put(i, false);
        }
    }
}
