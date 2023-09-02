package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.application.model.AttackContext;
import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.MagicService;
import com.asgames.ataliasflame.domain.services.SpellService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;

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

    @Transactional
    public Character castSpell(String characterReference, SpellName spellName) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Spell spell = SPELLS.get(spellName);
        if (spell == null) {
            throw new IllegalStateException("Unknown spell: " + spellName);
        }
        magicService.castSpell(character, spell);
        return characterRepository.save(character);
    }

    @Transactional
    public AttackContext castAttackSpell(String characterReference, SpellName spellName, String monsterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Spell spell = SPELLS.get(spellName);
        if (spell == null) {
            throw new IllegalStateException("Unknown spell: " + spellName);
        }
        Monster monster = locationAdventureService.getMonster(monsterReference);
        magicService.castAttackSpell(character, spell, monster);

        return AttackContext.builder()
                .character(characterRepository.save(character))
                .monster(monsterRepository.save(monster))
                .build();
    }

    @Transactional
    public Character removeBlessingMagic(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        magicService.removeBlessingMagic(character);
        return characterRepository.save(character);
    }

    @Transactional(readOnly = true)
    public List<Spell> listCharacterSpells(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        return spellService.listSpells(character);
    }
}
