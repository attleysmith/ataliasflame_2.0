package com.asgames.ataliasflame.domain.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DiceUtilsTest {

    @Test
    void roll10Statistics() {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            rolls.add(DiceUtils.roll10());
        }
        System.out.println("Roll 1 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 1).count());
        System.out.println("Roll 2 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 2).count());
        System.out.println("Roll 3 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 3).count());
        System.out.println("Roll 4 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 4).count());
        System.out.println("Roll 5 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 5).count());
        System.out.println("Roll 6 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 6).count());
        System.out.println("Roll 7 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 7).count());
        System.out.println("Roll 8 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 8).count());
        System.out.println("Roll 9 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 9).count());
        System.out.println("Roll 10 out of 1000 (cca. 100): " + rolls.stream().filter(roll -> roll == 10).count());
    }

    @Test
    void successStatistics() {
        for (int i = 0; i < 10; i++) {
            int successCount = 0;
            for (int j = 0; j < 100000; j++) {
                if (DiceUtils.successX(1)) {
                    successCount++;
                }
            }
            System.out.println("1% success out of 100000 (cca. 1000): " + successCount);
        }
    }
}