package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

public class prob2 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(new java.io.File("day4/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        int sum = 0;
        String line;
        int cardNum = 1;
        Map<Integer, Integer> cardCounts = new HashMap<>();

        while((line = br.readLine()) != null) {
            //Add one to this card count for the 1 original
            cardCounts.put(cardNum, cardCounts.getOrDefault(cardNum, 0) + 1);

            String[] split1 = line.strip().split(":")[1].split("\\|");

            String[] winners = split1[0].strip().split("\\s+");
            String[] myNums = split1[1].strip().split("\\s+");

            int winnings = 0;
            for (String num : myNums) {
                if(isInArray(num, winners)) {
                    winnings++;
                }
            }

            
            //Add winnings to the next N card counts
            for(int i=1; i<=winnings; i++) {
                cardCounts.put(cardNum+i, cardCounts.getOrDefault(cardNum+i, 0)+cardCounts.get(cardNum));
            }

            cardNum++;
        }

        sum = cardCounts.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    private static boolean isInArray(String num, String[] winners) {
        return Arrays.stream(winners).anyMatch(x -> x.equals(num));
    }
}
