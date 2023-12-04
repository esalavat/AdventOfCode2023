package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import day3.prob1.Point;

public class prob1 {
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }
        public int getX() {
            return x;
        }

        public void setY(int y) {
            this.y = y;
        }
        public int getY() {
            return y;
        }
    }
 
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(new java.io.File("day3/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        int sum = 0;
        String line;

        List<List<Character>> blueprintList = new ArrayList<>();

        int lineNum = 0;
        while((line = br.readLine()) != null) {

            List<Character> newRow = new ArrayList<>();
            int colNum = 0;
            
            for(int i=0; i<line.length(); i++) {
                newRow.add(line.charAt(i));
            }

            blueprintList.add(newRow);
        }

        Character[][] blueprintGrid = blueprintList.stream().map(x -> x.toArray(new Character[0])).collect(Collectors.toList()).toArray(new Character[0][0]);

        boolean isPartNumber = false;
        String currentNum = "";

        for(int y=0; y<blueprintGrid.length; y++) {
            for(int x=0; x<blueprintGrid.length; x++) {
                Character currentChar = blueprintGrid[y][x];
                if(isNumber(currentChar)) {
                    currentNum += currentChar;
                    if(isSymbolAdjacent(blueprintGrid, x, y)) {
                        isPartNumber = true;
                    }    
                }
                if(isSymbol(currentChar) || isPeriod(currentChar) || isEndOfLine(blueprintGrid, x, y)) {
                    if(isPartNumber && currentNum.length() > 0) {
                        sum += Integer.parseInt(currentNum);
                    }
                    currentNum = "";
                    isPartNumber = false;
                }
            }
        }

        System.out.println(sum);
    }

    private static boolean isEndOfLine(Character[][] blueprintGrid, int x, int y) {
        return x == blueprintGrid[y].length-1;
    }

    private static boolean isSymbolAdjacent(Character[][] blueprintGrid, int x, int y) {
        
        Set<Point> points = new HashSet<>();

        points.add(new Point(x-1, y-1));
        points.add(new Point(x-1, y));
        points.add(new Point(x-1, y+1));
        points.add(new Point(x, y+1));
        points.add(new Point(x, y-1));
        points.add(new Point(x+1, y-1));
        points.add(new Point(x+1, y));
        points.add(new Point(x+1, y+1));

        for (Point point : points) {
            if(isValidPoint(blueprintGrid, point.getX(), point.getY()) && isSymbol(blueprintGrid[point.getY()][point.getX()])) {
                return true;
            }
        }

        return false;
    }

    private static boolean isValidPoint(Character[][] blueprintGrid, int x, int y) {
        int ySize = blueprintGrid.length;
        int xSize = blueprintGrid[0].length;

        if(x < 0 || x >= xSize || y < 0 || y >= ySize) {
            return false;
        }

        return true;
    }

    private static boolean isPeriod(Character input) {
        return input.equals('.');
    }

    private static boolean isNumber(Character input) {
        return Character.isDigit(input);
    }

    private static boolean isSymbol(Character input) {
        return !Character.isDigit(input) && !input.equals('.');
    }
}
