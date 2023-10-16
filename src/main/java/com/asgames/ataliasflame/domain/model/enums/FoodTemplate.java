package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Food;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;

@AllArgsConstructor
public enum FoodTemplate implements ItemTemplate {
    WATER(3, 0),
    BREAD(5, 0),
    FRUIT(8, 2),
    MEAT(10, 1),
    HEALING_HERB(20, 10);

    private final int healingEffect;
    private final int magicEffect;

    @Override
    public ItemType getType() {
        return FOOD;
    }

    public Food instance() {
        return Food.builder()
                .reference(UUID.randomUUID().toString())
                .code(name())
                .type(getType())
                .healingEffect(healingEffect)
                .magicEffect(magicEffect)
                .build();
    }
}
