package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class prob1 {

    private static final int RED_MAX = 12;
    private static final int GREEN_MAX = 13;
    private static final int BLUE_MAX = 14;
    
    private static enum Color {
        red(12),
        green(13),
        blue(14);

        private int max;

        Color(int max) {
            this.max = max;
        }

        public int getMax() {
            return this.max;
        }
    }

    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader(new java.io.File("day2/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        int sum = 0;
        String line;


        while((line = br.readLine()) != null) {
            Map<Color, Integer> maxCounts = new HashMap<>();

            String[] firstSplit = line.split(":");
            int gameNum = Integer.parseInt(firstSplit[0].split(" ")[1]);

            String[] games = firstSplit[1].split(";");

            for(int i=0; i<games.length; i++) {
                String[] cubes = games[i].split(",");

                for(int j=0; j<cubes.length; j++) {
                    String[] cubeSplit = cubes[j].strip().split(" ");
                    int count = Integer.parseInt(cubeSplit[0]);
                    Color color = Color.valueOf(cubeSplit[1]);

                    Integer previousCount = maxCounts.get(color);

                    if(previousCount == null || count > previousCount) {
                        maxCounts.put(color, count);
                    }
                }
            }

            boolean possible = true;

            for (Entry<Color,Integer> entrySet : maxCounts.entrySet()) {
                Color color = entrySet.getKey();
                int value = entrySet.getValue();

                if(value > color.getMax()) {
                    possible = false;
                }
            }

            if(possible) {
                sum += gameNum;
            }
        }

        System.out.println(sum);
    }
}
