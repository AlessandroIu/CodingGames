package codingame.thereIsNoSpoon;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    static boolean[][] grid;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis

        grid = new boolean [width][height];

        if (in.hasNextLine()) {
            String line = in.nextLine();
        }
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            // System.err.println(line);

        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // Three coordinates: a node, its right neighbor, its bottom neighbor
        System.out.println("0 0 1 0 0 1");
    }
}