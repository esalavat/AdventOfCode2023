package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class prob1 {
    public static void main(String[] args) throws IOException {
        
        FileReader fr = new FileReader(new java.io.File("day1/input1.txt").getAbsolutePath());
        
        String line;

        BufferedReader br = new BufferedReader(fr);

        int sum = 0;

        while((line = br.readLine()) != null) {
            Character first = null;
            Character last = null;

            for(int i=0; i<line.length(); i++) {
                char currentChar = line.charAt(i);

                if(Character.isDigit(currentChar)) {
                    if(first == null) {
                        first = currentChar;
                    } 
                    
                    last = currentChar;
                }    
            }
            
            if(first != null) {
                int currentCode = Integer.parseInt(first.toString() + last);
                sum += currentCode;
            }
        }
        
        System.out.println(sum);
    }
}