package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ArmorType.BODY_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.HELMET;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;

@AllArgsConstructor
public enum ArmorTemplate implements ItemTemplate {
    CAP(HELMET, 0, 10, 10),
    LEATHER_HELMET(HELMET, 1, 20, 20),
    CHAIN_HOOD(HELMET, 2, 35, 35),
    METAL_HELMET(HELMET, 3, 50, 50),
    LINEN_ARMOR(BODY_ARMOR, 0, 10, 50),
    LEATHER_ARMOR(BODY_ARMOR, 2, 25, 60),
    STUDDED_LEATHER_ARMOR(BODY_ARMOR, 3, 30, 80),
    CHAIN_MAIL(BODY_ARMOR, 4, 40, 100),
    PLATE_MAIL(BODY_ARMOR, 6, 50, 100),
    FULL_PLATE_MAIL(BODY_ARMOR, 8, 60, 120);

    private final ArmorType armorType;
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
                .armorType(armorType)
                .defense(defense)
                .absorption(absorption)
                .durability(Energy.withTotal(durability))
                .build();
    }
}
