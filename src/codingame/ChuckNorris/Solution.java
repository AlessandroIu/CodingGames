package codingame.ChuckNorris;

import java.util.Scanner;

/**
 * https://www.codingame.com/training/easy/chuck-norris
 * <p>
 * Binary with 0 and 1 is good, but binary with only 0, or almost, is even better!
 * Originally, this is a concept designed by Chuck Norris to send so called unary messages.
 * <p>
 * Write a program that takes an incoming message as input and displays as output the message encoded using Chuck Norris’ method.
 * <p>
 * Rules
 * Here is the encoding principle:
 * <p>
 * - The input message consists of ASCII characters (7-bit)
 * - The encoded output message consists of blocks of 0
 * - A block is separated from another block by a space
 * - Two consecutive blocks are used to produce a series of same value bits (only 1 or 0 values):
 * - First block: it is always 0 or 00. If it is 0, then the series contains 1, if not, it contains 0
 * - Second block: the number of 0 in this block is the number of bits in the series
 * <p>
 * Example
 * Let’s take a simple example with a message which consists of only one character: Capital C. C in binary is represented as 1000011, so with Chuck Norris’ technique this gives:
 * <p>
 * - 0 0 (the first series consists of only a single 1)
 * - 00 0000 (the second series consists of four 0)
 * - 0 00 (the third consists of two 1)
 * So C is coded as: 0 0 00 0000 0 00
 * <p>
 * <p>
 * Second example, we want to encode the message CC (i.e. the 14 bits 10000111000011) :
 * <p>
 * - 0 0 (one single 1)
 * - 00 0000 (four 0)
 * - 0 000 (three 1)
 * - 00 0000 (four 0)
 * - 0 00 (two 1)
 * So CC is coded as: 0 0 00 0000 0 000 00 0000 0 00
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        // Converting to binary sequence
        String sequenceOf0And1 = "";
        for (int i = 0; i < message.length(); i++) {
            String conversionInProgress = Integer.toBinaryString(message.charAt(i));
            if (conversionInProgress.length() == 6) {
                conversionInProgress = "0" + conversionInProgress;
            }
            sequenceOf0And1 = sequenceOf0And1.concat(conversionInProgress);
        }

        // Creating the Chuck norris sequence
        String sequenceOf0 = "";
        char currentChar = sequenceOf0And1.charAt(0);
        int numerosityOfSequence = 1;
        for (int i = 1; i < sequenceOf0And1.length(); i++) {
            if (sequenceOf0And1.charAt(i) == currentChar) {
                numerosityOfSequence++;
            } else {
                if (currentChar == '0') {
                    sequenceOf0 = sequenceOf0 + "00 ";
                    for (int j = 0; j < numerosityOfSequence; j++) {
                        sequenceOf0 = sequenceOf0 + "0";
                    }
                    sequenceOf0 = sequenceOf0 + " ";
                } else {
                    sequenceOf0 = sequenceOf0 + "0 ";
                    for (int j = 0; j < numerosityOfSequence; j++) {
                        sequenceOf0 = sequenceOf0 + "0";
                    }
                    sequenceOf0 = sequenceOf0 + " ";
                }
                currentChar = sequenceOf0And1.charAt(i);
                numerosityOfSequence = 1;
            }
        }

        // Managing the last character
        if (numerosityOfSequence != 0) {
            if (currentChar == '0') {
                sequenceOf0 = sequenceOf0 + "00 ";
                for (int j = 0; j < numerosityOfSequence; j++) {
                    sequenceOf0 = sequenceOf0 + "0";
                }
                sequenceOf0 = sequenceOf0 + " ";
            } else {
                sequenceOf0 = sequenceOf0 + "0 ";
                for (int j = 0; j < numerosityOfSequence; j++) {
                    sequenceOf0 = sequenceOf0 + "0";
                }
                sequenceOf0 = sequenceOf0 + " ";
            }
        }

        System.out.println(sequenceOf0.trim());
    }
}