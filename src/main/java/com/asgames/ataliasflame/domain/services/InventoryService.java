package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.valueobjects.Weapon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.STARTING_WEAPON_SELECTOR;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.FOOD;
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
        character.setWeapon(startingWeapon);

        characterCalculationService.recalculateProperties(character);
    }

    public void use(Character character, Item item) {
        if (!item.getType().equals(FOOD)) {
            throw new UnsupportedOperationException("Only food can be used!");
        }
        healingService.eat(character, item);
    }
}
