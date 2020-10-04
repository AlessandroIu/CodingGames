import java.util.Scanner;

/**
 * https://www.codingame.com/training/easy/the-descent
 * Rules
 * At the start of each game turn, you are given the height of the 8 mountains from left to right.
 * By the end of the game turn, you must fire on the highest mountain by outputting its index (from 0 to 7).
 * Firing on a mountain will only destroy part of it, reducing its height. Your ship descends after each pass.
 * <p>
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            int indexOfHighestMountain = 0;
            int heightOfHighestMountain = 0;
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); // represents the height of one mountain.
                if (mountainH >= heightOfHighestMountain) {
                    heightOfHighestMountain = mountainH;
                    indexOfHighestMountain = i;
                }
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(indexOfHighestMountain); // The index of the mountain to fire on.
        }
    }
}