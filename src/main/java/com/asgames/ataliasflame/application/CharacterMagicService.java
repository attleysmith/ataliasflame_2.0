package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.TargetContext;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.MagicService;
import com.asgames.ataliasflame.domain.services.SpellService;
import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacterMagicService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    private LocationAdventureService locationAdventureService;

    @Autowired
    private MagicService magicService;
    @Autowired
    private SpellService spellService;

    @Autowired
    private SpellRegistry spellRegistry;

    @Transactional
    public Character castSpell(String characterReference, SpellName spellName) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Spell spell = spellRegistry.get(spellName);
        if (spellService.unknownSpell(character, spell)) {
            throw new IllegalArgumentException("The character is not familiar with the spell!");
        }
        magicService.castSpell(character, spell, null);
        return characterRepository.save(character);
    }

    @Transactional
    public TargetContext castTargetingSpell(String characterReference, SpellName spellName, String monsterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Spell spell = spellRegistry.get(spellName);
        if (spellService.unknownSpell(character, spell)) {
            throw new IllegalArgumentException("The character is not familiar with the spell!");
        }
        Monster targetMonster = locationAdventureService.getMonster(monsterReference);
        magicService.castSpell(character, spell, targetMonster);

        return TargetContext.builder()
                .character(characterRepository.save(character))
                .monster(monsterRepository.save(targetMonster))
                .build();
    }

    @Transactional(readOnly = true)
    public List<Spell> listCharacterSpells(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        return spellService.listSpells(character);
    }
}
