package com.asgames.ataliasflame.domain.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DiceUtilsTest {

    @Test
    void statistics() {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            rolls.add(DiceUtils.roll10());
        }
        System.out.println("Roll1: " + rolls.stream().filter(roll -> roll == 1).count());
        System.out.println("Roll2: " + rolls.stream().filter(roll -> roll == 2).count());
        System.out.println("Roll3: " + rolls.stream().filter(roll -> roll == 3).count());
        System.out.println("Roll4: " + rolls.stream().filter(roll -> roll == 4).count());
        System.out.println("Roll5: " + rolls.stream().filter(roll -> roll == 5).count());
        System.out.println("Roll6: " + rolls.stream().filter(roll -> roll == 6).count());
        System.out.println("Roll7: " + rolls.stream().filter(roll -> roll == 7).count());
        System.out.println("Roll8: " + rolls.stream().filter(roll -> roll == 8).count());
        System.out.println("Roll9: " + rolls.stream().filter(roll -> roll == 9).count());
        System.out.println("Roll10: " + rolls.stream().filter(roll -> roll == 10).count());
    }
}