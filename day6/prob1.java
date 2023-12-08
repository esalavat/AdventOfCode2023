package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class prob1 {
        public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(new java.io.File("day6/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        String line;

        line = br.readLine();
        int[] times = Arrays.stream(line.split(":")[1].strip().split("\\s+")).mapToInt(x -> Integer.parseInt(x.strip())).toArray();

        line = br.readLine();
        int[] distances = Arrays.stream(line.split(":")[1].strip().split("\\s+")).mapToInt(x -> Integer.parseInt(x.strip())).toArray();

        List<Integer> amountThatWon = new ArrayList<>();

        for(int i=0; i<times.length; i++) {
            int count = 0;
            
            int time= times[i];
            int distance = distances[i];

            int lastT = 0;
            for(int t=0; t<time; t++) {
                int thisDistance = t*(time-t);

                if(thisDistance<=distance) {
                    count++;
                    lastT = t;
                    //System.out.println(thisDistance + " | " + t + " | " + count);
                } else {
                    //System.out.println("break: " + thisDistance + " | " + t + " | " + count);
                    break;
                }
            }

            for(int t=time; t>lastT; t--) {
                int thisDistance = t*(time-t);

                if(thisDistance<=distance) {
                    count++;
                    //System.out.println(thisDistance + " | " + t + " | " + count);
                } else {
                    //System.out.println("break: " + thisDistance + " | " + t + " | " + count);
                    break;
                }
            }

            //System.out.println(time + " | " + count + " | " + (time+1-count));
            amountThatWon.add(time+1-count);
        }

        System.out.println(amountThatWon.stream().reduce((a,b) -> a*b).get());
    }
}
