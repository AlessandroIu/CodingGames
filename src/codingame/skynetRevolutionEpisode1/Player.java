package codingame.skynetRevolutionEpisode1;

import java.util.Scanner;

/**
 * https://www.codingame.com/training/medium/skynet-revolution-episode-1
 * <p>
 * Your virus has caused a backdoor to open on the Skynet network enabling you to send new instructions in real time.
 * You decide to take action by stopping Skynet from communicating on its own internal network.
 * Skynet's network is divided into several smaller networks, in each sub-network is a Skynet agent tasked with
 * transferring information by moving from node to node along links and accessing gateways leading to other sub-networks.
 * Your mission is to reprogram the virus so it will sever links in such a way that the Skynet Agent is unable to access another sub-network thus preventing information concerning the presence of our virus to reach Skynet's central hub.
 * <p>
 * Rules
 * For each test you are given:
 * - A map of the network.
 * - The position of the exit gateways.
 * - The starting position of the Skynet agent.
 * >>> Nodes can only be connected to up to a single gateway. <<<
 * <p>
 * Each game turn:
 * - First off, you sever one of the given links in the network.
 * - Then the Skynet agent moves from one Node to another accessible Node.
 * <p>
 * <p>
 * Victory Conditions: The Skynet agent cannot reach an exit gateway
 * <p>
 * Lose Conditions: The Skynet agent has reached a gateway
 * <p>
 * Example
 * Initialization input
 * 4 4 1
 * 0 1
 * 0 2
 * 1 3
 * 2 3
 * 3
 * Text representation of the network used in this example. There are 4 nodes, 4 links and 1 gateway.
 * The next 4 lines describe the links. The last integer is the index of the exit node.
 * <p>
 * Turn 1
 * The Skynet agent starts at node 0 (SI = 0). Our virus cut the link between the nodes 1 and 3.
 * <p>
 * Turn 2
 * The Skynet agent moves to node 2 (SI = 2). Our virus cut the link between the nodes 2 and 3.
 * <p>
 * Turn 3
 * The Skynet agent has been cut off from the exit, you have won !
 * <p>
 * Note
 * The tests provided are similar to the validation tests used to compute the final score but remain different.
 * <p>
 * Game Input
 * The program must first read the initialization data from standard input. Then, within an infinite loop, read the
 * data from the standard input related to the current state of the Skynet agent and provide to the standard output
 * the next instruction.
 * <p>
 * Initialization input
 * Line 1: 3 integers N L E
 * - N, the total number of nodes in the level, including the gateways.
 * - L, the number of links in the level.
 * - E, the number of exit gateways in the level.
 * Next L lines: 2 integers per line (N1, N2), indicating a link between the nodes indexed N1 and N2 in the network.
 * Next E lines: 1 integer EI representing the index of a gateway node.
 * <p>
 * Input for one game turn
 * Line 1: 1 integer SI, which is the index of the node on which the Skynet agent is positioned this turn.
 * <p>
 * Output for one game turn
 * A single line comprised of two integers C1 and C2 separated by a space. C1 and C2 are the indices of the nodes you wish to sever the link between.
 * <p>
 * Constraints
 * - 2 ≤ N ≤ 500
 * - 1 ≤ L ≤ 1000
 * - 1 ≤ E ≤ 20
 * - 0 ≤ N1, N2 < N
 * - 0 ≤ SI < N
 * - 0 ≤ C1, C2 < N
 * - Response time per turn ≤ 150ms
 **/
class Player {

    static boolean[][] adjacencyMatrix;
    static int[] exitsVector;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numberOfNodes = in.nextInt(); // The total number of nodes in the level, including the gateways
        int numberOfLinks = in.nextInt(); // The number of links
        int numberOfExitGateways = in.nextInt(); // The number of exit gateways

        adjacencyMatrix = new boolean[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfLinks; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();

            // Populating the adjacency matrix
            adjacencyMatrix[N1][N2] = true;
            adjacencyMatrix[N2][N1] = true;
        }

        exitsVector = new int[numberOfExitGateways];
        for (int i = 0; i < numberOfExitGateways; i++) {
            // Populating the exit nodes vector
            exitsVector[i] = in.nextInt(); // The index of a gateway node
        }

        // game loop
        while (true) {
            int skynetPosition = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            int gatewayNearToSkynet = findGatewayNearToSkynet(skynetPosition);
            if (gatewayNearToSkynet != -1) {
                System.out.println(gatewayNearToSkynet + " " + skynetPosition);
                adjacencyMatrix[skynetPosition][gatewayNearToSkynet] = false;
                adjacencyMatrix[gatewayNearToSkynet][skynetPosition] = false;
            } else {
                int[] linkToCut = findARandomLink();
                System.out.println(linkToCut[0] + " " + linkToCut[1]);
                adjacencyMatrix[linkToCut[0]][linkToCut[1]] = false;
                adjacencyMatrix[linkToCut[1]][linkToCut[0]] = false;
            }
        }
    }

    private static int[] findARandomLink() {
        int[] linkVector = new int[2];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j]) {
                    linkVector[0] = i;
                    linkVector[1] = j;
                }
            }
        }
        return linkVector;
    }

    private static int findGatewayNearToSkynet(int skynetPosition) {
        for (int exitGatewayIndex : exitsVector) {
            if (adjacencyMatrix[skynetPosition][exitGatewayIndex]) {
                return exitGatewayIndex;
            }
        }
        return -1;
    }

}