package codingame.powerOfThorEpisode1;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 *
 * https://www.codingame.com/training/easy/power-of-thor-episode-1
 * ---
 * Your program must allow Thor to reach the light of power.
 *
 *  Rules
 * Thor moves on a map which is 40 wide by 18 high. Note that the coordinates (X and Y) start at the top left!
 * This means the most top left cell has the coordinates "X=0,Y=0" and the most bottom right one has the coordinates "X=39,Y=17".
 *
 * Once the program starts you are given:
 * the variable lightX: the X position of the light of power that Thor must reach.
 * the variable lightY: the Y position of the light of power that Thor must reach.
 * the variable initialTX: the starting X position of Thor.
 * the variable initialTY: the starting Y position of Thor.
 * At the end of the game turn, you must output the direction in which you want Thor to go among:
 * - N (North)
 * - NE (North-East)
 * - E (East)
 * - SE (South-East)
 * - S (South)
 * - SW (South-West)
 * - W (West)
 * - NW (North-West)
 * Each movement makes Thor move by 1 cell in the chosen direction.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTx = in.nextInt(); // Thor's starting X position
        int initialTy = in.nextInt(); // Thor's starting Y position

        int remainingX = initialTx - lightX;
        int remainingY = initialTy - lightY;

        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            String nextMove = "";

            System.err.println("remaining X " + remainingX);
            System.err.println("remaining Y " + remainingY);
            if(remainingY > 0) {
                nextMove = nextMove + "N";
                remainingY--;
            } else if (remainingY < 0){
                nextMove = nextMove + "S";
                remainingY++;
            }

            if(remainingX > 0) {
                nextMove = nextMove + "W";
                remainingX--;
            } else if (remainingX < 0){
                nextMove = nextMove + "E";
                remainingX++;
            }

            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(nextMove);
        }
    }
}