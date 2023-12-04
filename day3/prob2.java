package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class prob2 {
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

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }

            if (o.getClass() != this.getClass()) {
                return false;
            }

            final Point other = (Point) o;

            if(this.x != other.getX()) return false;
            if(this.y != other.getY()) return false;

            return true;
        }
    }
 
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(new java.io.File("day3/input1.txt").getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);

        int sum = 0;
        String line;

        List<List<Character>> blueprintList = new ArrayList<>();

        while((line = br.readLine()) != null) {

            List<Character> newRow = new ArrayList<>();
            
            for(int i=0; i<line.length(); i++) {
                newRow.add(line.charAt(i));
            }

            blueprintList.add(newRow);
        }

        Character[][] blueprintGrid = blueprintList.stream().map(x -> x.toArray(new Character[0])).collect(Collectors.toList()).toArray(new Character[0][0]);

        for(int y=0; y<blueprintGrid.length; y++) {
            for(int x=0; x<blueprintGrid.length; x++) {
                Character currentChar = blueprintGrid[y][x];
                if(isGear(currentChar)) {
                    Set<Point> adjacentNumbers = getAdjacentNums(blueprintGrid, x, y);

                    Set<Integer> fullNums = findFullNums(adjacentNumbers, blueprintGrid); 

                    if(fullNums.size() == 2) {
                        sum += fullNums.stream().reduce(1, (a,b) -> a*b);
                    }
                }
            }
        }

        System.out.println(sum);
    }

    private static Set<Integer> findFullNums(Set<Point> adjacentNumbers, Character[][] blueprintGrid) {
        
        Set<Integer> returnNums = new HashSet<>();

        Set<Point> ignore = new HashSet<>();
        String currentNum = "";
        for(Point point : adjacentNumbers) {
            
            currentNum += blueprintGrid[point.getY()][point.getX()];
            
            int y = point.getY();
            int x = point.getX()-1;
            
            while(isValidPoint(blueprintGrid, x, y) && isNumber(blueprintGrid[y][x]) && !isInSet(ignore, x, y)) {
                currentNum = blueprintGrid[y][x] + currentNum;
                if(isInSet(adjacentNumbers, x, y)) {
                    ignore.add(new Point(x, y));
                }
                x--;
            }

            x = point.getX()+1;
            while(isValidPoint(blueprintGrid, x, y) && isNumber(blueprintGrid[y][x]) && !isInSet(ignore, x, y)) {
                currentNum += blueprintGrid[y][x];
                if(isInSet(adjacentNumbers, x, y)) {
                    ignore.add(new Point(x, y));
                }
                x++;
            }

            returnNums.add(Integer.parseInt(currentNum));
            currentNum = "";
        }

        return returnNums;
    }

    private static boolean isInSet(Set<Point> returnNums, int x, int y) {
        return returnNums.contains(new Point(x,y));
    }

    private static boolean isGear(Character currentChar) {
        return currentChar.equals('*');
    }

    private static Set<Point> getAdjacentNums(Character[][] blueprintGrid, int x, int y) {
        
        Set<Point> points = new HashSet<>();

        points.add(new Point(x-1, y-1));
        points.add(new Point(x-1, y));
        points.add(new Point(x-1, y+1));
        points.add(new Point(x, y+1));
        points.add(new Point(x, y-1));
        points.add(new Point(x+1, y-1));
        points.add(new Point(x+1, y));
        points.add(new Point(x+1, y+1));

        Set<Point> returnVals = new HashSet<>();

        for (Point point : points) {
            if(isValidPoint(blueprintGrid, point.getX(), point.getY()) && isNumber(blueprintGrid[point.getY()][point.getX()])) {
                returnVals.add(new Point(point.getX(), point.getY()));
            }
        }

        return returnVals;
    }

    private static boolean isValidPoint(Character[][] blueprintGrid, int x, int y) {
        int ySize = blueprintGrid.length;
        int xSize = blueprintGrid[0].length;

        if(x < 0 || x >= xSize || y < 0 || y >= ySize) {
            return false;
        }

        return true;
    }

    private static boolean isNumber(Character input) {
        return Character.isDigit(input);
    }
}
