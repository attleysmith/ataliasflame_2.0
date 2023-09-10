package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.enums.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Spell {

    private final SpellName name;
    private final MagicType type;
    private final SpellGroup group;
    private final Integer cost;
    private final int minDamage;
    private final int maxDamage;
    private final int healingEffect;

    private final List<Caste> prohibitedCastes;
    private final List<Race> prohibitedRaces;

    public int averageDamage() {
        return (minDamage + maxDamage) / 2;
    }
}
