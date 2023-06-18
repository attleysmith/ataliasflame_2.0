package com.asgames.ataliasflame.domain.services;

import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll;
import static java.lang.Math.max;
import static java.lang.Math.round;
import static java.util.Arrays.stream;

@Service
public class CalculatorService {

    public int calculate(int base, int... multipliers) {
        int multiplier = stream(multipliers).reduce(0, Integer::sum);

        return max(0, round(base + (base * multiplier / 100f)));
    }

    public <T> T choose(List<SelectionValue<T>> partitions) {
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
