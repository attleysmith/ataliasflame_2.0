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
        Random random = new Random();
        int hit = random.nextInt(100) + 1;
        return hit < chance;
    }
}
