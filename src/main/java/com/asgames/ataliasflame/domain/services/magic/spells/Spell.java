package com.asgames.ataliasflame.domain.services.magic.spells;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.DamageService;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.asgames.ataliasflame.domain.model.enums.CompanionType.SOUL_CHIP;

public abstract class Spell {

    @Autowired
    protected StoryLineLogger storyLineLogger;

    @Autowired
    protected DamageService damageService;

    @Getter
    protected final SpellName name;
    @Getter
    protected final SpellGroup group;
    @Getter
    protected final MagicType type;

    public Spell(SpellName name, SpellGroup group, MagicType type) {
        this.name = name;
        this.group = group;
        this.type = type;
    }

    public abstract void enforce(Character character, Monster targetMonster);

    public abstract int getCost();

    public int averageDamage() {
        return 0;
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
}
