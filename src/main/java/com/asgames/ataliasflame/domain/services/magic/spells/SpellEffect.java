package com.asgames.ataliasflame.domain.services.magic.spells;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class SpellEffect {

    @Autowired
    protected StoryLineLogger storyLineLogger;

    protected final SpellName spellName;

    public SpellEffect(SpellName spellName) {
        this.spellName = spellName;
    }

    public abstract void enforce(Character character, Monster monster);

    public SpellName getSpellName() {
        return spellName;
    }

    protected List<SoulChip> listUnusedSouls(Character character) {
        List<SoulChip> unusedSouls = new ArrayList<>(character.getSoulChips());
        List<String> companionReferences = character.getCompanions().stream().map(Companion::getReference).collect(toList());
        for (SoulChip soulChip : character.getSoulChips()) {
            if (companionReferences.contains(soulChip.getReference())
                    || character.getBlessings().contains(soulChip.getShape().name())) {
                unusedSouls.remove(soulChip);
            }
        }
        return unusedSouls;
    }
}
