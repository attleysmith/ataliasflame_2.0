package com.asgames.ataliasflame.domain.utils;

import java.util.Random;

public final class DiceUtils {

    private static final int DEFAULT_POSSIBILITES = 100;

    private DiceUtils() {
        // utility class
    }

    public static boolean successX(int chance) {
        if (chance <= 0) {
            return false;
        }
        int hit = roll();
        return hit < chance;
    }

    public static int roll() {
        return roll(DEFAULT_POSSIBILITES);
    }

    public static int roll(int possibilities) {
        Random random = new Random();
        return random.nextInt(possibilities) + 1;
    }
}
