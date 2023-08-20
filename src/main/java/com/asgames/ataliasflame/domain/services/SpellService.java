package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static java.util.stream.Collectors.toList;

@Service
public class SpellService {

    public List<Spell> listSpellsByType(Character character, MagicType magicType) {
        return SPELLS.values().stream()
                .filter(spell -> !CASTE_SPELL_PROHIBITION.get(character.getCaste())
                        .contains(spell.getName()))
                .filter(spell -> !RACE_SPELL_PROHIBITION.get(character.getRace())
                        .contains(spell.getName()))
                .filter(spell -> spell.getType().equals(magicType))
                .collect(toList());
    }
}
