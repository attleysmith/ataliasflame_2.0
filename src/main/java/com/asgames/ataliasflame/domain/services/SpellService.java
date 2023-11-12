package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.CasteGroup;
import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SpellService {

    private final SpellRegistry spellRegistry;

    public boolean unknownSpell(Character character, Spell spell) {
        return !listSpells(character).contains(spell);
    }

    public List<Spell> listSpells(Character character) {
        return spellRegistry.get().stream()
                .filter(spell -> {
                    Set<CasteGroup> prohibitedCasteGroups = new HashSet<>();
                    prohibitedCasteGroups.addAll(spell.getGroup().prohibitedCasteGroups);
                    prohibitedCasteGroups.addAll(spell.getName().prohibitedCasteGroups);
                    return !prohibitedCasteGroups.containsAll(character.getCaste().groupTags);
                })
                .filter(spell -> !spell.getGroup().prohibitedCastes.contains(character.getCaste()))
                .filter(spell -> !spell.getGroup().prohibitedRaces.contains(character.getRace()))
                .filter(spell -> !spell.getName().prohibitedCastes.contains(character.getCaste()))
                .filter(spell -> !spell.getName().prohibitedRaces.contains(character.getRace()))
                .toList();
    }
}
