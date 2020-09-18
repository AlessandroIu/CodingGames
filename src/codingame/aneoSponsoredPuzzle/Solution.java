package codingame.aneoSponsoredPuzzle;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * https://www.codingame.com/training/medium/aneo
 *
 * Driving your car, you enter a section of road and you plan to rest entirely on your cruise control to cross the area
 * without having to stop or slow down.
 *
 * The goal is to find the maximum speed (off speeding) that will allow you to cross all the traffic lights to green.
 *
 **/



/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int speed = in.nextInt();
        int lightCount = in.nextInt();

//        System.out.println("Speed: " + speed);
//        System.out.println("Light count: " + lightCount);

        int[][] distanceAndDuration = new int[lightCount][2];

        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();
            distanceAndDuration[i][0] = distance;
            distanceAndDuration[i][1] = duration;
        }

//        System.out.println(Arrays.deepToString(distanceAndDuration));

        // TODO: try to pass all traffic lights

        for (int speedTrying = speed; speedTrying > 0 ; speedTrying--) {
            double speedMSTrying = speedTrying/3.6;
            if (checkIfPassesAllTrafficLightsAtCurrentSpeed(lightCount, distanceAndDuration, speedMSTrying)){
                BigDecimal bigDecimal = new BigDecimal(String.valueOf(speedMSTrying*3.6));
                int succesfulSpeedInKmH = bigDecimal.intValue();
                System.out.println(succesfulSpeedInKmH);
                return;
            };
        }

    }

    static boolean checkIfPassesAllTrafficLightsAtCurrentSpeed(int lightCount, int[][] distanceAndDuration, double speedMSTrying){
        boolean passesAll = true;
        for (int i = 0; i < lightCount; i++) {
            boolean passes = checkIfPassesNextTrafficLight(distanceAndDuration[i][0], distanceAndDuration[i][1], speedMSTrying);
            if (!passes) {
                passesAll = false;
                break;
            }
        }
        return passesAll;
    }

    static boolean checkIfPassesNextTrafficLight(double nextTrafficDistance, double nextTrafficDuration, double speedTrying){
        BigDecimal nextTrafficDistanceBD = new BigDecimal(String.valueOf(nextTrafficDistance));
        BigDecimal nextTrafficDurationBD = new BigDecimal(String.valueOf(nextTrafficDuration));
        BigDecimal speedTryingBD = new BigDecimal(String.valueOf(speedTrying));
        BigDecimal speedDividerBD = new BigDecimal("3.6");

        BigDecimal speedInMSBD = speedTryingBD.divide(speedDividerBD, MathContext.DECIMAL128);
        BigDecimal timeForReachingTrafficLightBD = nextTrafficDistanceBD.divide(speedInMSBD, MathContext.DECIMAL128);
        BigDecimal greenRedRoundsNumberBD = timeForReachingTrafficLightBD.divide(nextTrafficDurationBD, MathContext.DECIMAL128);

        BigDecimal greenRedRoundsNumberRounded = greenRedRoundsNumberBD.round(MathContext.DECIMAL32);
        int greenRedRoundsNumberIntegerPart = greenRedRoundsNumberRounded.intValue();

        return greenRedRoundsNumberIntegerPart % 2 == 0;
    }

}