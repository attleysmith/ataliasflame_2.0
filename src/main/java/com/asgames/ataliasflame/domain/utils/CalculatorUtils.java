package com.asgames.ataliasflame.domain.utils;

import java.util.List;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll;
import static java.lang.Math.*;

public final class CalculatorUtils {

    private CalculatorUtils() {
        // utility class
    }

    public static int average(int a, int b) {
        return (a + b) / 2;
    }

    public static int weight(int a, int shareA, int b, int shareB) {
        return ((a * shareA) + (b * shareB)) / (shareA + shareB);
    }

    public static int percent(int base, int multiplier) {
        return round(base * multiplier / 100f);
    }

    public static int calculatePercentValueUp(int base, int value) {
        return (int) ceil((float) value / base * 100);
    }

    public static int calculatePercentValueDown(int base, int value) {
        return (int) floor((float) value / base * 100);
    }

    public static int calculate(int base, int multiplier) {
        if (base < 0) {
            throw new IllegalArgumentException("Simple calculation shouldn't be made on negative base value!");
        }
        return max(0, base + percent(base, multiplier));
    }

    public static int calculate(int base, List<Integer> multipliers) {
        int multiplier = multipliers.stream().reduce(0, Integer::sum);

        return calculate(base, multiplier);
    }

    public static int pointOut(int min, int max) {
        if (min == max) {
            return min;
        }
        if (min > max) {
            throw new IllegalArgumentException("Minimum cannot be greater than maximum!");
        }
        int possibilities = max - min + 1;
        return roll(possibilities) + min - 1;
    }

    public static <T> T pointOut(List<T> list) {
        int pointer = roll(list.size()) - 1;
        return list.get(pointer);
    }

    public static <T> T choose(List<SelectionValue<T>> partitions) {
        int possibilities = partitions.stream()
                .map(SelectionValue::getChance)
                .reduce(0, Integer::sum);
        int selectedValue = roll(possibilities);

        int progress = 0;
        T selection = null;
        for (SelectionValue<T> partition : partitions) {
            progress = progress + partition.getChance();
            if (selectedValue <= progress) {
                selection = partition.getValue();
                break;
            }
        }
        return selection;
    }
}
