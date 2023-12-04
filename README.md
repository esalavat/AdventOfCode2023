# AdventOfCode2023
My solutions to Advent of Code 2023. Java edition.

# Running

Use javac and java to run each prob.java file.

For example, to run day1 prob1 use:

    javac day1/prob1.java && java day1.prob1

# Solutions

## Day 4
### Problem 1

I just line by line use string.split to split them into the groups of numbers.  Then loop through my numbers and check if they're in the winning numbers.  Then add them up(multiply by 2 each time) and get the answer.

## Day 3
### Problem 1

I loaded the input file into a List<List<Character>> because it's basically an array that can grow in size, then converted that to a Character[][] 2d array.  Then we loop through that 2d array and keep track of any numbers, and when we're on a number spot we check if any symbols are adjacent to it.  Then just sum up the ones that have symbols next to them.

### Problem 2

Taking the first part of problem 1, I redid the loop to look for *(Gears) instad of numbers and symbols.  From those positions I got a set of all adjacent number digits.  Then I looped through those digits and traveled left and right in the x direction to build the full number.  While doing this I had to make sure we didn't double count any of the adjacent digits again as new numbers so I built an ignore list if I hit those numbers while building another number.

## Day 2
### Problem 1

I made an enum that stores the colors with the max allowed.  Then just loop through each line, parse out the info needed and store the max of each color for each game.  Then at the end I check all the maxes against what's allowed and count up the game ID sum.

### Problem 2 

From problem 1 I already had the min of each color.  I just had to multiply them together and sum that up.

## Day 1
### Problem 1

I just read each line and loop through the characters in the line and keep track of the first and last one seen.

### Problem 2

I decided I didn't want to use brute force or replacement.  So I built a buffer system where it keeps track of each digit and resets them as needed.  This way we only have to go through the input once.