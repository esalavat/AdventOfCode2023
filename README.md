# AdventOfCode2023
My solutions to Advent of Code 2023. Java edition.

# Running

Use javac and java to run each prob.java file.

For example, to run day1 prob1 use:

    javac day1/prob1.java && java day1.prob1

# Solutions

## Day2
### Problem1

I made an enum that stores the colors with the max allowed.  Then just loop through each line, parse out the info needed and store the max of each color for each game.  Then at the end I check all the maxes against what's allowed and count up the game ID sum.

## Day1
### Problem1

I just read each line and loop through the characters in the line and keep track of the first and last one seen.

### Problem2

I decided I didn't want to use brute force or replacement.  So I built a buffer system where it keeps track of each digit and resets them as needed.  This way we only have to go through the input once.