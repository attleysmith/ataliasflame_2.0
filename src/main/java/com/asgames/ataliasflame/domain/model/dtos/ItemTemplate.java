package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.enums.ItemType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ItemTemplate {

    private final String code;
    private final ItemType type;
    private final int healingEffect;
    private final int magicEffect;

    public Item instance() {
        return Item.builder()
                .reference(UUID.randomUUID().toString())
                .code(code)
                .type(type)
                .healingEffect(healingEffect)
                .magicEffect(magicEffect)
                .build();
    }
}
