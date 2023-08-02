package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.valueobjects.Energy;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class Monster implements Combatant {

    private String code;
    private int attack;
    private int defense;
    private int minDamage;
    private int maxDamage;
    private Energy health;
    private int initiative;

    private int experience;
}
