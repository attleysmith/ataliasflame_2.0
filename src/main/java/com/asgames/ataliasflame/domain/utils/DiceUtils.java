package com.asgames.ataliasflame.domain.utils;

import java.util.Random;

public final class DiceUtils {

    private DiceUtils() {
        // utility class
    }

    public static boolean successX(int chance) {
        if (chance <= 0) {
            return false;
        }
        int hit = roll100();
        return hit <= chance;
    }

    public static int roll10() {
        return roll(10);
    }

    public static int roll100() {
        return roll(100);
    }

    public static int roll(int possibilities) {
        Random random = new Random();
        return random.nextInt(possibilities) + 1;
    }
}
