package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import com.asgames.ataliasflame.domain.model.vos.Energy;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.SHIELD;

public enum ShieldTemplate implements ItemTemplate {
    BUCKLER(3, 25, 30),
    ROUND_SHIELD(5, 30, 50),
    KITE_SHIELD(8, 35, 70),
    TOWER_SHIELD(10, 50, 100);

    ShieldTemplate(int defense, int absorption, int durability) {
        this.defense = defense;
        this.absorption = absorption;
        this.durability = durability;
    }

    private final int defense;
    private final int absorption;
    private final int durability;

    @Override
    public ItemType getType() {
        return SHIELD;
    }

    public Shield instance() {
        return Shield.builder()
                .reference(UUID.randomUUID().toString())
                .code(name())
                .type(getType())
                .defense(defense)
                .absorption(absorption)
                .durability(Energy.withTotal(durability))
                .build();
    }
}