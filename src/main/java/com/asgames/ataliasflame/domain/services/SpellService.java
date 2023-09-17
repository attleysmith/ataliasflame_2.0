package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellService {

    @Autowired
    private SpellRegistry spellRegistry;

    public boolean unknownSpell(Character character, Spell spell) {
        return !listSpells(character).contains(spell);
    }

    public List<Spell> listSpells(Character character) {
        return spellRegistry.get().stream()
                .filter(spell -> !spell.getName().prohibitedCastes.contains(character.getCaste()))
                .filter(spell -> !spell.getName().prohibitedRaces.contains(character.getRace()))
                .filter(spell -> !spell.getGroup().prohibitedCastes.contains(character.getCaste()))
                .filter(spell -> !spell.getGroup().prohibitedRaces.contains(character.getRace()))
                .toList();
    }
}
