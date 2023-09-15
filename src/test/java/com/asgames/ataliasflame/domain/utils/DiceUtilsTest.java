package com.asgames.ataliasflame.domain.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.MonsterTemplate.DRAGON;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;
import static java.math.RoundingMode.HALF_UP;

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

    @Test
    void dragonSpawnStatistics() {
        int total = 100000;
        Map<Integer, Integer> appearances = new HashMap<>();
        for (int i = 0; i < total; i++) {
            int count = 0;
            do {
                count++;
            } while (successX(DRAGON.getSpawn()));
            int occurrence = appearances.getOrDefault(count, 0);
            appearances.put(count, occurrence + 1);
        }

        appearances.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    int count = entry.getKey();
                    int occurrence = entry.getValue();
                    System.out.println(count + ": " + occurrence + "(" + percentage(occurrence, total) + "%) -- cca. " + referenceValue(count) + "%");
                });
    }

    private static BigDecimal percentage(int dividendValue, int divisorValue) {
        BigDecimal dividend = BigDecimal.valueOf(dividendValue).setScale(5, HALF_UP);
        BigDecimal divisor = BigDecimal.valueOf(divisorValue);

        return dividend.divide(divisor, HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(3, HALF_UP);
    }

    private static BigDecimal referenceValue(int count) {
        BigDecimal result = BigDecimal.ONE.setScale(7, HALF_UP);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                result = result.multiply(BigDecimal.valueOf(100 - DRAGON.getSpawn()));
            } else {
                result = result.multiply(BigDecimal.valueOf(DRAGON.getSpawn())).divide(BigDecimal.valueOf(100), HALF_UP);
            }
        }
        return result;
    }
}