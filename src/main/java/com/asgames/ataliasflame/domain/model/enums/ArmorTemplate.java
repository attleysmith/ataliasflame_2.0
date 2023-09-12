package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import com.asgames.ataliasflame.domain.model.vos.Energy;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;

public enum ArmorTemplate implements ItemTemplate {
    LINEN_ARMOR(0, 10, 50),
    LEATHER_ARMOR(3, 25, 60),
    STUDDED_LEATHER_ARMOR(5, 30, 80),
    CHAIN_MAIL(6, 40, 100),
    PLATE_MAIL(8, 50, 100),
    FULL_PLATE_MAIL(10, 60, 120);

    ArmorTemplate(int defense, int absorption, int durability) {
        this.defense = defense;
        this.absorption = absorption;
        this.durability = durability;
    }

    private final int defense;
    private final int absorption;
    private final int durability;

    @Override
    public ItemType getType() {
        return ARMOR;
    }

    public Armor instance() {
        return Armor.builder()
                .reference(UUID.randomUUID().toString())
                .code(name())
                .type(getType())
                .defense(defense)
                .absorption(absorption)
                .durability(Energy.withTotal(durability))
                .build();
    }
}
