package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public class prob2 {
    public static void main(String[] args) throws IOException {
        
        FileReader fr = new FileReader(new java.io.File("day1/input1.txt").getAbsolutePath());
        
        String line;

        BufferedReader br = new BufferedReader(fr);

        int sum = 0;
        Map<String, String> buffers = new HashMap<>();
        Map<String, Integer> digitMap = getDigitMap(); 

        
        while((line = br.readLine()) != null) {
            Integer first = null;
            Integer last = null;
            initBuffers(buffers, digitMap);
        
            for(int i=0; i<line.length(); i++) {
                char currentChar = line.charAt(i);

                addToBuffers(currentChar, buffers);

                Optional<Integer> match = bufferMatch(buffers, digitMap);
                if(match.isPresent()) {
                    if(first == null) {
                        first = match.get();
                    } 
                    
                    last = match.get();
                }    
            }
            
            if(first != null) {
                int currentCode = (first * 10) + last;
                sum += currentCode;

                System.out.println(currentCode + " | " + first + " | " + last);
            }
        }
        
        System.out.println(sum);
    }

    private static void addToBuffers(char currentChar, Map<String, String> buffers) {
        for (Entry<String,String> buffersEntry : buffers.entrySet()) {
            String digit = buffersEntry.getKey();
            String currentBuff = buffersEntry.getValue() + currentChar;

            if(currentBuff.length() > digit.length() || !currentBuff.equals(digit.substring(0, currentBuff.length()))) {
                if(digit.equals(""+currentChar) || digit.substring(0, 1).equals(""+currentChar)) {
                    currentBuff = "" + currentChar;
                } else {
                    currentBuff = "";
                }
            }

            buffers.put(digit, currentBuff);
            //System.out.println(digit + "|" + currentBuff);
        }
        //System.out.println("----------------------");
    }

    private static void initBuffers(Map<String, String> buffers, Map<String, Integer> digitMap) {
        for (Entry<String,Integer> digitMapEntry : digitMap.entrySet()) {
            buffers.put(digitMapEntry.getKey(), "");
        }
    }

    private static Optional<Integer> bufferMatch(Map<String, String> buffers, Map<String, Integer> digitMap) {
        
        for (Entry<String,String> entrySet : buffers.entrySet()) {
            if(entrySet.getKey().equals(entrySet.getValue())) {
                return Optional.of(digitMap.get(entrySet.getKey()));
            }
        }
        
        return Optional.empty();
    }

    private static Map<String, Integer> getDigitMap() {
        Map<String, Integer> digitMap = new HashMap<>();

        digitMap.put("one", 1);
        digitMap.put("two", 2);
        digitMap.put("three", 3);
        digitMap.put("four",4);
        digitMap.put("five", 5);
        digitMap.put("six", 6);
        digitMap.put("seven", 7);
        digitMap.put("eight", 8);
        digitMap.put("nine", 9);
        digitMap.put("1", 1);
        digitMap.put("2", 2);
        digitMap.put("3", 3);
        digitMap.put("4",4);
        digitMap.put("5", 5);
        digitMap.put("6", 6);
        digitMap.put("7", 7);
        digitMap.put("8", 8);
        digitMap.put("9", 9);

        return digitMap;
    }
}