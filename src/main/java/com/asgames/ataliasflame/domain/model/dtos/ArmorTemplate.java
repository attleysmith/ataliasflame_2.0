package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;

@Builder
@Data
public class ArmorTemplate {

    private String code;
    private int defense;
    private int absorption;
    private int durability;

    public Armor instance() {
        return Armor.builder()
                .reference(UUID.randomUUID().toString())
                .code(code)
                .type(ARMOR)
                .defense(defense)
                .absorption(absorption)
                .durability(Energy.withTotal(durability))
                .build();
    }
}
