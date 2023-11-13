package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.TargetContext;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.MagicService;
import com.asgames.ataliasflame.domain.services.SpellService;
import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterMagicService {

    private final CharacterRepository characterRepository;
    private final MonsterRepository monsterRepository;
    private final CharacterMaintenanceService characterMaintenanceService;
    private final MagicService magicService;
    private final SpellService spellService;
    private final SpellRegistry spellRegistry;

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
        Location location = character.getLocation();
        Monster targetMonster = location.getMonsters().stream()
                .filter(locationMonster -> locationMonster.getReference().equals(monsterReference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Referenced monster is not at the character's location!"));
        magicService.castSpell(character, spell, targetMonster);

        return TargetContext.builder()
                .monster(monsterRepository.save(targetMonster))
                .character(characterRepository.save(character))
                .build();
    }

    @Transactional(readOnly = true)
    public List<Spell> listCharacterSpells(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        return spellService.listSpells(character);
    }
}
