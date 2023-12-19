package com.asgames.ataliasflame.application.spells;

import com.asgames.ataliasflame.application.CharacterMagicService;
import com.asgames.ataliasflame.application.CharacterTestBase;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class RaceSpellsTestBase extends CharacterTestBase {

    @Autowired
    protected CharacterMagicService characterMagicService;

    protected static List<SpellName> spellNamesOf(List<Spell> spellList) {
        return spellList.stream()
                .map(Spell::getName)
                .toList();
    }
}
