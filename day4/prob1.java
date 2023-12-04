package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class prob1 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(new java.io.File("day4/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        int sum = 0;
        String line;
        int cardNum = 1;

        while((line = br.readLine()) != null) {
            String[] split1 = line.strip().split(":")[1].split("\\|");

            String[] winners = split1[0].strip().split("\\s+");
            String[] myNums = split1[1].strip().split("\\s+");

            int winnings = 0;
            for (String num : myNums) {
                if(isInArray(num, winners)) {
                    if(winnings == 0) {
                        winnings = 1;
                    } else {
                        winnings *= 2;
                    }
                }
            }

            sum += winnings;
        }

        System.out.println(sum);
    }

    private static boolean isInArray(String num, String[] winners) {
        return Arrays.stream(winners).anyMatch(x -> x.equals(num));
    }
}
