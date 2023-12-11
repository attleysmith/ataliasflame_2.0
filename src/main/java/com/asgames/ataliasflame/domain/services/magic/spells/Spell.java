package com.asgames.ataliasflame.domain.services.magic.spells;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.domain.services.DamageService;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.MagicService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class Spell {

    @Autowired
    protected StoryLineLogger storyLineLogger;
    @Autowired
    protected DamageService damageService;
    @Autowired
    protected CharacterCalculationService characterCalculationService;
    @Autowired
    protected HealingService healingService;
    @Autowired
    protected MagicService magicService;

    @Getter
    protected final SpellName name;

    public Spell(SpellName name) {
        this.name = name;
    }

    protected void enforce(Character character, Monster targetMonster) {
        throw new UnsupportedOperationException("Arguments needed to enforce spell!");
    }

    public void enforce(Character character, Monster targetMonster, Map<String, String> args) {
        enforce(character, targetMonster);
    }

    public abstract int getCost();

    public abstract String getDetails();

    public int getMinDamage() {
        return 0;
    }

    public int getMaxDamage() {
        return 0;
    }

    public SpellGroup getGroup() {
        return name.group;
    }

    public MagicType getType() {
        return name.type;
    }

    public void validateArgs(Map<String, String> args) {
        if (args.size() > 0) {
            throw new IllegalArgumentException("Unknown arguments!");
        }
    }

    protected static String effectDetailsOf(Booster booster) {
        return booster.effects.entrySet().stream()
                .filter(effect -> effect.getValue() != 0)
                .map(effect -> effect.getKey().name() + ": " + effect.getValue() + "%")
                .collect(Collectors.joining("; "));
    }

    protected static String effectDetailsOf(int defense, int absorption, int durability) {
        return Map.of("Defense", defense, "Absorption", absorption, "Durability", durability).entrySet().stream()
                .filter(effect -> effect.getValue() != 0)
                .map(effect -> effect.getKey() + ": " + effect.getValue())
                .collect(Collectors.joining("; "));
    }

    protected static String effectDetailsOf(int attack, int defense, int damage, int health) {
        return Map.of("Attack", attack, "Defense", defense, "Damage", damage, "Health", health).entrySet().stream()
                .filter(effect -> effect.getValue() != 0)
                .map(effect -> effect.getKey() + ": " + effect.getValue() + "%")
                .collect(Collectors.joining("; "));
    }
}
