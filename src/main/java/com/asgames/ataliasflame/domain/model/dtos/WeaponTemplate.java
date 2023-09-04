package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.entities.Weapon;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.WEAPON;

@Builder
@Data
public class WeaponTemplate {

    private String code;
    private int minDamage;
    private int maxDamage;
    private int defense;
    private int initiative;
    private boolean oneHanded;

    public Weapon instance() {
        return Weapon.builder()
                .reference(UUID.randomUUID().toString())
                .code(code)
                .type(WEAPON)
                .minDamage(minDamage)
                .maxDamage(maxDamage)
                .defense(defense)
                .initiative(initiative)
                .oneHanded(oneHanded)
                .build();
    }
}
