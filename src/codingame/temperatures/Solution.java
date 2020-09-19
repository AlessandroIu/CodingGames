package codingame.temperatures;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * https://www.codingame.com/training/easy/temperatures
 *
 *
 * In this exercise, you have to analyze records of temperature to find the closest to zero.
 *
 * 	Rules
 * Write a program that prints the temperature closest to 0 among input data.
 * If two numbers are equally close to zero, positive integer has to be considered closest to zero
 * (for instance, if the temperatures are -5 and 5, then display 5).
 *
 *  Game Input
 * Your program must read the data from the standard input and write the result on the standard output.
 *
 *  Input
 * Line 1: N, the number of temperatures to analyze
 * Line 2: A string with the N temperatures expressed as integers ranging from -273 to 5526
 *
 *  Output
 * Display 0 (zero) if no temperatures are provided. Otherwise, display the temperature closest to 0.
 *
 *  Constraints
 * 0 ≤ N < 10000
 *
 **/

class Solution {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int closerTemperatureTo0 = 5526;
        if (n == 0) closerTemperatureTo0 = 0;
        for (int i = 0; i < n; i++) {
            int analyzingTemperature = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            int currentDistanceTo0 = Math.abs(closerTemperatureTo0);
            int analyzingDistanceTo0 = Math.abs(analyzingTemperature);
            boolean isAnalyzingPositive = analyzingTemperature > 0;
            if (analyzingDistanceTo0 < currentDistanceTo0) closerTemperatureTo0 = analyzingTemperature;
            if (analyzingDistanceTo0 == currentDistanceTo0 && isAnalyzingPositive) closerTemperatureTo0 = analyzingTemperature;
        }
        System.out.println(closerTemperatureTo0);
    }

}