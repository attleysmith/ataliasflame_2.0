package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.valueobjects.Weapon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.STARTING_WEAPON_SELECTOR;
import static com.asgames.ataliasflame.domain.MockConstants.WEAPONS;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.WEAPON;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Slf4j
@Service
public class InventoryService {
    @Autowired
    private CharacterCalculationService characterCalculationService;
    @Autowired
    private HealingService healingService;

    public void setStartingInventory(Character character) {
        Weapon startingWeapon = choose(STARTING_WEAPON_SELECTOR);
        log.info("Starting weapon: " + startingWeapon.getCode());
        takeWeapon(character, startingWeapon);
    }

    public void use(Character character, Item item) {
        switch (item.getType()) {
            case FOOD:
                healingService.eat(character, item);
                break;
            case WEAPON:
                changeWeapon(character, item);
                break;
            default:
                throw new UnsupportedOperationException("Not supported item usage: " + item.getType());
        }
    }

    public void changeWeapon(Character character, Item item) {
        if (!item.getType().equals(WEAPON)) {
            throw new IllegalArgumentException("Only weapon can be used as weapon!");
        }

        Weapon newWeapon = WEAPONS.get(item.getCode());
        if (newWeapon == null) {
            throw new IllegalStateException("New weapon not recognized as real weapon: " + item.getCode());
        }

        if (newWeapon.getPopularity() > character.getWeapon().getPopularity()) {
            takeWeapon(character, newWeapon);
            log.info("Weapon changed to " + newWeapon.getCode());
        }
    }

    private void takeWeapon(Character character, Weapon weapon) {
        character.setWeapon(weapon);
        characterCalculationService.recalculateProperties(character);
    }
}
