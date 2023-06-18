package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Weapon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.STARTING_WEAPON_DROPS;

@Slf4j
@Service
public class InventoryService {

    @Autowired
    private CalculatorService calculatorService;
    @Autowired
    private CharacterCalculationService characterCalculationService;

    public void setStartingInventory(Character character) {
        Weapon startingWeapon = calculatorService.choose(STARTING_WEAPON_DROPS);
        log.info("Starting weapon: " + startingWeapon.getCode());
        character.setWeapon(startingWeapon);

        characterCalculationService.recalculateProperties(character);
    }
}
