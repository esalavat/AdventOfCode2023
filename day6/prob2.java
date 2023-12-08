package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class prob2 {
        public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(new java.io.File("day6/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        String line;

        line = br.readLine();
        long time = Long.parseLong(Arrays.stream(line.split(":")[1].strip().split("\\s+")).reduce("", (a, b) -> a.strip()+b.strip()));

        line = br.readLine();
        long distance = Long.parseLong(Arrays.stream(line.split(":")[1].strip().split("\\s+")).reduce("", (a, b) -> a.strip()+b.strip()));

        List<Long> amountThatWon = new ArrayList<>();

            int count = 0;
            
            long lastT = 0;
            for(long t=0; t<time; t++) {
                long thisDistance = t*(time-t);

                if(thisDistance<=distance) {
                    count++;
                    lastT = t;
                    //System.out.println(thisDistance + " | " + t + " | " + count);
                } else {
                    //System.out.println("break: " + thisDistance + " | " + t + " | " + count);
                    break;
                }
            }

            for(long t=time; t>lastT; t--) {
                long thisDistance = t*(time-t);

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
        

        System.out.println(amountThatWon.stream().reduce((a,b) -> a*b).get());
    }
}
