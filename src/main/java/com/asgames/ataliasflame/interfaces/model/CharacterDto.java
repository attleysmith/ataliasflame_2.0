package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.*;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Builder
@Data
public class CharacterDto {
    private String reference;
    private String name;
    private Gender gender;
    private Race race;
    private Caste caste;
    private God defensiveGod;
    private int attack;
    private int defense;
    private int minDamage;
    private int maxDamage;
    private int damageMultiplier;
    private int initiative;
    private int level;
    private int experience;
    private int attributePoints;
    private int totalHealth;
    private int injury;
    private int totalMagicPoint;
    private int usedMagicPoint;
    private Map<Attribute, Integer> attributes;
    private Set<SoulChipDto> soulChips;
    private Set<CompanionDto> companions;
    private Set<ActiveBlessingDto> blessings;
    private WeaponDto primaryWeapon;
    private WeaponDto secondaryWeapon;
    private ShieldDto shield;
    private CoverDto cover;
    private String locationReference;
}
