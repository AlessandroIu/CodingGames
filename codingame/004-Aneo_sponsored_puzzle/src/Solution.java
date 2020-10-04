import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

/**
 * https://www.codingame.com/training/medium/aneo
 * <p>
 * Driving your car, you enter a section of road and you plan to rest entirely on your cruise control to cross the area
 * without having to stop or slow down.
 * <p>
 * The goal is to find the maximum speed (off speeding) that will allow you to cross all the traffic lights to green.
 **/

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int speed = in.nextInt();
        int lightCount = in.nextInt();

        int[][] distanceAndDuration = new int[lightCount][2];

        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();
            distanceAndDuration[i][0] = distance;
            distanceAndDuration[i][1] = duration;
        }

        // Trying to pass all traffic lights with a given integer speed expressed in Km/h
        for (int speedTrying = speed; speedTrying > 0; speedTrying--) {
            double speedMSTrying = speedTrying / 3.6;
            // Checking if the car passes at the current speed, otherwise try with 1 Km/h slower
            if (checkIfPassesAllTrafficLightsAtCurrentSpeed(lightCount, distanceAndDuration, speedMSTrying)) {
                BigDecimal bigDecimal = new BigDecimal(String.valueOf(speedMSTrying * 3.6));
                int successfulSpeedInKmH = bigDecimal.intValue();
                System.out.println(successfulSpeedInKmH);
                return;
            }
            ;
        }

    }

    static boolean checkIfPassesAllTrafficLightsAtCurrentSpeed(int lightCount, int[][] distanceAndDuration, double speedMSTrying) {
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

    static boolean checkIfPassesNextTrafficLight(double nextTrafficDistance, double nextTrafficDuration, double speedTrying) {
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