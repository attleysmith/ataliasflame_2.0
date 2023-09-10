package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static java.util.stream.Collectors.toList;

@Service
public class SpellService {

    public boolean unknownSpell(Character character, Spell spell) {
        return !listSpells(character).contains(spell);
    }

    public List<Spell> listSpells(Character character) {
        return SPELLS.values().stream()
                .filter(spell -> !spell.getProhibitedCastes().contains(character.getCaste()))
                .filter(spell -> !spell.getProhibitedRaces().contains(character.getRace()))
                .filter(spell -> !spell.getGroup().prohibitedCastes.contains(character.getCaste()))
                .filter(spell -> !spell.getGroup().prohibitedRaces.contains(character.getRace()))
                .collect(toList());
    }
}
