package com.asgames.ataliasflame.application;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.*;
import com.asgames.ataliasflame.infrastructure.repositories.CharacterRepository;
import com.asgames.ataliasflame.infrastructure.repositories.DefensiveGodConversionLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CharacterAdventureService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterMaintenanceService characterMaintenanceService;
    @Autowired
    private CombatService combatService;
    @Autowired
    private MagicService magicService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private HealingService healingService;
    @Autowired
    private MonsterService monsterService;
    @Autowired
    private DefensiveGodConversionService defensiveGodConversionService;
    @Autowired
    private DefensiveGodConversionLogRepository defensiveGodConversionLogRepository;

    @Transactional
    public Character sleep(String characterName) {
        Character character = characterMaintenanceService.getCharacter(characterName);
        healingService.sleep(character);
        magicService.sleep(character);
        return characterRepository.save(character);
    }

    @Transactional
    public Character combat(String characterName) {
        Character character = characterMaintenanceService.getCharacter(characterName);
        Monster monster = monsterService.getRandomMonster();

        magicService.castMagic(character, monster);
        combatService.combat(List.of(character), List.of(monster));
        if (character.getActualHealth() > 0) {
            character = experienceService.gainExperience(character, monster.getExperience());
            log.info("You are the winner!");
            log.info("Remaining health: " + character.getActualHealth());
            monsterService.looting(character, monster);
        } else {
            log.info("You are defeated!");
            log.info("Enemy's health: " + monster.getActualHealth());
        }

        return characterRepository.save(character);
    }

    @Transactional
    public String getDefensiveGodConversionCode(String characterName) {
        Character character = characterMaintenanceService.getCharacter(characterName);
        String conversionCode = defensiveGodConversionService.getConversionCode(character);

        DefensiveGodConversionLog conversionLog = defensiveGodConversionLogRepository.save(DefensiveGodConversionLog.builder()
                .conversionCode(conversionCode)
                .cleric(character)
                .god(character.getDefensiveGod())
                .build());

        return conversionLog.getConversionCode();
    }
}
