package com.asgames.ataliasflame.domain.services.magic.spells;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class Spell {

    @Autowired
    protected StoryLineLogger storyLineLogger;

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

    protected List<SoulChip> listUnusedSouls(Character character) {
        List<SoulChip> unusedSouls = new ArrayList<>(character.getSoulChips());
        List<String> companionReferences = character.getCompanions().stream().map(Companion::getReference).toList();
        for (SoulChip soulChip : character.getSoulChips()) {
            if (companionReferences.contains(soulChip.getReference())
                    || character.getBlessings().contains(soulChip.getShape().name())) {
                unusedSouls.remove(soulChip);
            }
        }
        return unusedSouls;
    }
}
