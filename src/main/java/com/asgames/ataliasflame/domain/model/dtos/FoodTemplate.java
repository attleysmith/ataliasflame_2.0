package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Food;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;

@Builder
@Data
public class FoodTemplate {

    private final String code;
    private final int healingEffect;
    private final int magicEffect;

    public Food instance() {
        return Food.builder()
                .reference(UUID.randomUUID().toString())
                .code(code)
                .type(FOOD)
                .healingEffect(healingEffect)
                .magicEffect(magicEffect)
                .build();
    }
}
