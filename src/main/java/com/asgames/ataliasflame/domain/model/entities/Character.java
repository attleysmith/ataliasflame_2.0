package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.*;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
public class Character implements Combatant {

    private String name;
    private Gender gender;
    private Race race;
    private Caste caste;
    private God defensiveGod;

    private int attack;
    private int defense;
    private int damage;
    private int damageMultiplier;
    private int totalHealth;
    private int injury;

    private int level;
    private int experience;
    private int attributePoints;

    private Map<Attribute, Integer> attributes;

    @Override
    public String getCode() {
        return name;
    }

    public Map<Attribute, Integer> getAttributes() {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        return attributes;
    }
}
