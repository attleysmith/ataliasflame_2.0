package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class Monster implements Combatant {

    private String code;
    private int attack;
    private int defense;
    private int damage;
    private int totalHealth;
    private int injury;
    private int initiative;

    private int experience;
}
