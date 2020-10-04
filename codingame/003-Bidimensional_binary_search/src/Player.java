import java.util.Scanner;

/**
 * https://www.codingame.com/ide/puzzle/shadows-of-the-knight-episode-1
 * <p>
 * Batman will look for the hostages on a given building by jumping from one window to another using his grapnel gun.
 * Batman's goal is to jump to the window where the hostages are located in order to disarm the bombs.
 * Unfortunately he has a limited number of jumps before the bombs go off...
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        int searchingLeft = 0;
        int searchingRight = W;
        int searchingTop = 0;
        int searchingBottom = H;

        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // the location of the next window Batman should jump to.

            switch (bombDir) {
                case "U":
                    searchingBottom = Y0 - 1;
                    break;
                case "UR":
                    searchingLeft = X0 + 1;
                    searchingBottom = Y0 - 1;
                    break;
                case "R":
                    searchingLeft = X0 + 1;
                    break;
                case "DR":
                    searchingLeft = X0 + 1;
                    searchingTop = Y0 + 1;
                    break;
                case "D":
                    searchingTop = Y0 + 1;
                    break;
                case "DL":
                    searchingRight = X0 - 1;
                    searchingTop = Y0 + 1;
                    break;
                case "L":
                    searchingRight = X0 - 1;
                    break;
                case "UL":
                    searchingRight = X0 - 1;
                    searchingBottom = Y0 - 1;
                    break;
            }

            X0 = (searchingLeft + searchingRight) / 2;
            Y0 = (searchingTop + searchingBottom) / 2;

            System.out.println(X0 + " " + Y0);
        }
    }
}