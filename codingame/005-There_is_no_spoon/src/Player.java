import java.util.Scanner;

/**
 * https://www.codingame.com/training/medium/there-is-no-spoon-episode-1
 * <p>
 * The game is played on a rectangular grid with a given size. Some cells contain power nodes.
 * The rest of the cells are empty.
 * The goal is to find, when they exist, the horizontal and vertical neighbors of each node.
 * <p>
 * RULES
 * To do this, you must find each (x1,y1) coordinates containing a node, and display the (x2,y2) coordinates of the
 * next node to the right, and the (x3,y3) coordinates of the next node to the bottom within the grid.
 * If a neighbor does not exist, you must output the coordinates -1 -1 instead of (x2,y2) and/or (x3,y3).
 * You lose if:
 * - you give an incorrect neighbor for a node;
 * - you give the neighbors for an empty cell;
 * - you compute the same node twice;
 * - you forget to compute the neighbors of a node.
 **/
class Player {

    // Write an action using System.out.println()
    // To debug: System.err.println("Debug messages...");

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis

        if (in.hasNextLine()) {
            in.nextLine();
        }

        // Creating a boolean grid (bi-dimensional array) representative of the input
        boolean[][] grid = new boolean[height][width];
        // Analyzing each line
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either "0" or "."
            boolean[] lineArray = new boolean[line.length()];
            for (int j = 0; j < line.length(); j++) {
                boolean isPresent = false;
                if (line.charAt(j) == '0') isPresent = true;
                lineArray[j] = isPresent;
            }
            grid[i] = lineArray;
        }

        // Analyzing the above created grid and computing the output
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                boolean currentCell = grid[row][column];
                // Skipping cell analyzing if it doesn't contains a node
                if (!currentCell) continue;

                int[] rightNeighbour = findingNeighbourToTheRight(column, row, grid[row]);
                int[] downNeighbour = findingNeighbourDown(column, row, grid);

                // Three coordinates: a node, its right neighbor, its bottom neighbor
                System.out.println(column + " " + row + " "
                        + rightNeighbour[0] + " " + rightNeighbour[1] + " "
                        + downNeighbour[0] + " " + downNeighbour[1]);
            }
        }
    }

    static int[] findingNeighbourToTheRight(int startingColumn, int startingRow, boolean[] row) {
        int[] neighbourToTheRightPosition = {-1, -1};
        for (int columnChecking = startingColumn + 1; columnChecking < row.length; columnChecking++) {
            if (row[columnChecking]) {
                neighbourToTheRightPosition[0] = columnChecking;
                neighbourToTheRightPosition[1] = startingRow;
                return neighbourToTheRightPosition;
            }
        }
        return neighbourToTheRightPosition;
    }

    static int[] findingNeighbourDown(int startingColumn, int startingRow, boolean[][] grid) {
        int[] neighbourDownPosition = {-1, -1};
        for (int rowChecking = startingRow + 1; rowChecking < grid.length; rowChecking++) {
            if (grid[rowChecking][startingColumn]) {
                neighbourDownPosition[0] = startingColumn;
                neighbourDownPosition[1] = rowChecking;
                return neighbourDownPosition;
            }
        }
        return neighbourDownPosition;
    }

}