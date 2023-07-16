package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.utils.CalculatorUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorUtilsTest {

    @ParameterizedTest
    @MethodSource("singleCalculations")
    void singleMultiplierTest(int base, int multiplier, int result) {
        assertThat(CalculatorUtils.calculate(base, multiplier), is(result));
    }

    @ParameterizedTest
    @MethodSource("manyCalculations")
    void manyMultipliersTest(int base, List<Integer> multiplier, int result) {
        assertThat(CalculatorUtils.calculate(base, multiplier), is(result));
    }

    private static Stream<Arguments> singleCalculations() {
        return Stream.of(
                arguments(0, 0, 0),
                arguments(0, 200, 0),
                arguments(1, 49, 1),
                arguments(1, 50, 2),
                arguments(1, 149, 2),
                arguments(1, 150, 3),
                arguments(5, 100, 10),
                arguments(5, 109, 10),
                arguments(5, 110, 11),
                arguments(80, 0, 80),
                arguments(80, 3, 82),
                arguments(80, -30, 56),
                arguments(80, -100, 0),
                arguments(80, -101, 0)
        );
    }

    private static Stream<Arguments> manyCalculations() {
        return Stream.of(
                arguments(1, List.of(10, 40), 2),
                arguments(1, List.of(20, 100, 30), 3),
                arguments(5, List.of(25, 25, 25, 25), 10),
                arguments(80, List.of(25, 25, -50), 80),
                arguments(80, List.of(-50, 53), 82),
                arguments(80, List.of(20, -50), 56)
        );
    }
}