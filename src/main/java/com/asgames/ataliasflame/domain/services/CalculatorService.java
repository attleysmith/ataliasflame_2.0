package com.asgames.ataliasflame.domain.services;

import org.springframework.stereotype.Service;

import static java.lang.Math.max;
import static java.lang.Math.round;
import static java.util.Arrays.stream;

@Service
public class CalculatorService {

    public int calculate(int base, int... multipliers) {
        int multiplier = stream(multipliers).reduce(0, Integer::sum);

        return max(0, round(base * (1 + multiplier / 100f)));
    }
}
