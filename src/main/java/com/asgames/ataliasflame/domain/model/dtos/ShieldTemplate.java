package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

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
                .defense(defense)
                .absorption(absorption)
                .durability(Energy.withTotal(durability))
                .build();
    }
}
