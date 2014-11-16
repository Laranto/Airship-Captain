package common;

import java.util.Random;

public class Utils {

    /**
     * Returns a number between and inclusive the min and max value
     */
    public static int getRandomIntBetween(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
