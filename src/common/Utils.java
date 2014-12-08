package common;

import java.util.Random;

/**
 * This file holds few functions which can be used
 * in the application
 *
 */
public class Utils {

    /**
     * int Returns a number between and inclusive the min and max value
     */
    public static int getRandomIntBetween(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
