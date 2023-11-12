package com.asgames.ataliasflame.domain.services.magic.spells;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.domain.services.DamageService;
import com.asgames.ataliasflame.domain.services.HealingService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.SOUL_CHIP;

public abstract class Spell {

    @Autowired
    protected StoryLineLogger storyLineLogger;
    @Autowired
    protected DamageService damageService;
    @Autowired
    protected CharacterCalculationService characterCalculationService;
    @Autowired
    protected HealingService healingService;

    @Getter
    protected final SpellName name;

    public Spell(SpellName name) {
        this.name = name;
    }

    public abstract void enforce(Character character, Monster targetMonster);

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

    protected List<SoulChip> listReadySouls(Character character) {
        List<SoulChip> unusedSouls = new ArrayList<>(character.getSoulChips());
        character.getCompanions().stream()
                .filter(companion -> companion.getType().equals(SOUL_CHIP))
                .map(companion -> ((SummonedSoulChip) companion).getSource())
                .forEach(unusedSouls::remove);
        character.getBlessings().stream()
                .map(ActiveBlessing::getSource)
                .filter(Objects::nonNull)
                .forEach(unusedSouls::remove);
        return unusedSouls.stream().filter(SoulChip::isReady).toList();
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
