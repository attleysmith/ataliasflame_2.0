package com.asgames.ataliasflame.domain.model.enums;

import com.asgames.ataliasflame.domain.model.entities.Weapon;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import lombok.AllArgsConstructor;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.WEAPON;

@AllArgsConstructor
public enum WeaponTemplate implements ItemTemplate {
    STAFF(1, 5, 5, -5, false),
    DAGGER(2, 6, 1, 0, true),
    SPEAR(2, 12, 5, -6, false),
    SWORD(2, 18, 3, -3, true);

    private final int minDamage;
    private final int maxDamage;
    private final int defense;
    private final int initiative;
    private final boolean oneHanded;

    @Override
    public ItemType getType() {
        return WEAPON;
    }

    public Weapon instance() {
        return Weapon.builder()
                .reference(UUID.randomUUID().toString())
                .code(name())
                .type(getType())
                .minDamage(minDamage)
                .maxDamage(maxDamage)
                .defense(defense)
                .initiative(initiative)
                .oneHanded(oneHanded)
                .build();
    }
}
