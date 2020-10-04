import java.util.Scanner;

/**
 * https://www.codingame.com/training/easy/dungeons-and-maps
 * <p>
 * Your adventure path led you to an inn in a small, forgotten town somewhere to the North of Golem Hills.
 * After gulping the last drop from the 9th mug of elf wine a shady old man materializes out of nowhere, in-front of you.
 * You start to doubt the wine.
 * The old man (throwing a pack of old maps on the table): Do you want to earn some good coins?
 * You (without looking at him): I've enough for food and wine!
 * The old man: What about a whole inn...!
 * You: Hm...
 * The old man: Yeees and you'll get the glory of being the first one to get to this treasure!
 * You (looking at the bunch of maps): But they look the same!?
 * The old man: Or do they, you must choose wisely.
 * The voice of the old man (from nowhere): Ah right, one more thing, beware of the Dragons!
 * You grab your staff and sword, swallow one more whole mug of wine:
 * Well, it's glory time!
 * <p>
 * You are given N maps for a dungeon. Each map may contain a path to a treasure T, from starting position
 * [ startRow; startCol ]. Determine the index of the map which holds the shortest path from the starting position to T,
 * but be careful a map may lead you to a TRAP.
 * <p>
 * A path is marked on the map with ^, v, <, > symbols, each corresponding to UP, DOWN, LEFT, RIGHT directions
 * respectively, i.e. each symbol shows you the next cell to move on.
 * <p>
 * A valid path must start from [ startRow; startCol ] and end on T.
 * <p>
 * The path length is the count of direction symbols plus 1, for the T cell.
 * <p>
 * Example:
 * W = 4 H = 4
 * startRow = 1 startCol = 1
 * N = 3
 * <p>
 * Maps:
 * 0
 * .>>v
 * .^#v
 * ..#v
 * ...T
 * <p>
 * 1
 * ....
 * .v#.
 * .v#.
 * .>>T
 * <p>
 * 2
 * ....
 * v<#.
 * v.#.
 * ..>T
 * <p>
 * <p>
 * In the above example map 2 does not contain a valid path from [1; 1] to T, map 0 contains a valid path with length 7
 * (the count of the direction symbols + T) and map 1 contains a valid path with length 5, so the answer is 1.
 * <p>
 * Input
 * Line 1: Width W and height H of the maps
 * Line 2: startRow and startCol for the starting position on the map
 * Line 3: An integer N for the number of maps to check
 * N * H Lines: Each H consecutive lines are representing a single map. Each line contains W characters representing a row of a map.
 * <p>
 * Characters can be:
 * . - Empty square
 * # - Wall
 * ^ - Move UP
 * v - Move DOWN
 * < - Move LEFT
 * > - Move RIGHT
 * T - The treasure square
 * <p>
 * Output
 * index of the map with the shortest path. If there isn't a map with valid path from [ startRow; startCol ] to T output TRAP.
 * Constraints
 * There is always a T on the maps.
 * If there are maps with valid path from [ startRow; startCol ] to T only one map holds the shortest path.
 * The given maps are representing the same dungeon, but the position for T may differ.
 * 0 < N < 10
 * 2 < W, H < 20
 * <p>
 * Constraints
 * There is always a T on the maps.
 * If there are maps with valid path from [ startRow; startCol ] to T only one map holds the shortest path.
 * The given maps are representing the same dungeon, but the position for T may differ.
 * 0 < N < 10
 * 2 < W, H < 20
 **/

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt(); // Width of the maps
        int h = in.nextInt(); // Height of the maps
        int startRow = in.nextInt();
        int startCol = in.nextInt();
        int numberOfMaps = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Map[] maps = new Map[numberOfMaps];

        for (int i = 0; i < numberOfMaps; i++) {
            char[][] mapGrid = new char[h][w];
            for (int j = 0; j < h; j++) {
                String mapRow = in.nextLine();
                for (int y = 0; y < mapRow.length(); y++) {
                    mapGrid[j][y] = mapRow.charAt(y);
                }
            }
            maps[i] = new Map(mapGrid, startRow, startCol);
        }

        int shortestPathInMaps = 9999999;
        int mapWithShortestPath = 0;
        boolean atLeastOneTreasureFound = false;
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].treasureFound) {
                atLeastOneTreasureFound = true;
                if (maps[i].pathLength < shortestPathInMaps) {
                    shortestPathInMaps = maps[i].pathLength;
                    mapWithShortestPath = i;
                }
            }
        }

        if (!atLeastOneTreasureFound) {
            System.out.println("TRAP");
        } else {
            System.out.println(mapWithShortestPath);
        }

    }

    static class Map {
        char[][] mapGrid;
        boolean[][] mapGridAlreadyPassed;
        int pathLength = 0;
        boolean treasureFound = false;

        Map(char[][] mapGrid, int startingRow, int startingColumn) {
            this.mapGrid = mapGrid;
            this.mapGridAlreadyPassed = new boolean[mapGrid.length][mapGrid[startingRow].length];

            int currentRow = startingRow;
            int currentColumn = startingColumn;
            int pathLengthTrying = 0;
            boolean finished = false;
            while (!finished) {
                if (currentRow < 0 || currentColumn < 0 || currentRow > (mapGrid.length - 1) || currentColumn > (mapGrid[currentRow].length - 1)) {
                    finished = true;
                    continue;
                }
                pathLengthTrying++;
                if (mapGridAlreadyPassed[currentRow][currentColumn]) {
                    finished = true;
                    continue;
                } else {
                    mapGridAlreadyPassed[currentRow][currentColumn] = true;
                }
                switch (mapGrid[currentRow][currentColumn]) {
                    case '>':
                        currentColumn++;
                        break;
                    case 'v':
                        currentRow++;
                        break;
                    case '^':
                        currentRow--;
                        break;
                    case '<':
                        currentColumn--;
                        break;
                    case 'T':
                        finished = true;
                        treasureFound = true;
                        pathLength = pathLengthTrying;
                        break;
                    case '.':
                    case '#':
                        finished = true;
                        break;
                }
            }
        }
    }

}