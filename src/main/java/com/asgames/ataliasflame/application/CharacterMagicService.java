package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.MagicService;
import com.asgames.ataliasflame.domain.services.SpellService;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;

@Slf4j
@Service
public class CharacterMagicService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private LocationRepository locationRepository;

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
    public Character castAttackMagic(String characterReference, String locationReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        Location location = locationAdventureService.getLocation(locationReference);
        List<Monster> monsters = location.getMonsters();

        magicService.castAttackMagic(character, monsters);

        locationRepository.save(location);
        return characterRepository.save(character);
    }

    @Transactional
    public Character removeBlessingMagic(String characterReference) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        magicService.removeBlessingMagic(character);
        return characterRepository.save(character);
    }

    @Transactional(readOnly = true)
    public List<Spell> listCharacterSpells(String characterReference, MagicType magicType) {
        Character character = characterMaintenanceService.getCharacter(characterReference);
        return spellService.listSpellsByType(character, magicType);
    }
}
