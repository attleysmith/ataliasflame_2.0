package com.asgames.ataliasflame.domain.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SoulChipShape {
    CANINE(0, "Shark"), APE_LIKE(1, "Monkey"), BIRD_OF_PREY(2, "Hawk");

    public final int order;
    public final String name;

    public static SoulChipShape valueByOrder(int order) {
        for (SoulChipShape shape : values()) {
            if (shape.order == order) {
                return shape;
            }
        }
        throw new IllegalArgumentException("Soul chip shape does not exists!");
    }
}
