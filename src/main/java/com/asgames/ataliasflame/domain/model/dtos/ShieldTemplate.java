package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.SHIELD;

@Builder
@Data
public class ShieldTemplate {

    private String code;
    private int defense;
    private int absorption;
    private int durability;

    public Shield instance() {
        return Shield.builder()
                .reference(UUID.randomUUID().toString())
                .code(code)
                .type(SHIELD)
                .defense(defense)
                .absorption(absorption)
                .durability(Energy.withTotal(durability))
                .build();
    }
}
