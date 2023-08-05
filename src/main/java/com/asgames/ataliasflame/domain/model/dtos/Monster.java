package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Monster implements Combatant {

    private final String code;
    private final int attack;
    private final int defense;
    private final int minDamage;
    private final int maxDamage;
    private final Energy health;
    private final int initiative;

    private final int experience;
}
