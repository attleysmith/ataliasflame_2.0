package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.Item;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.vos.Armor;
import com.asgames.ataliasflame.domain.model.vos.Shield;
import com.asgames.ataliasflame.domain.model.vos.Weapon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Slf4j
@Service
public class InventoryService {

    @Autowired
    private CharacterCalculationService characterCalculationService;
    @Autowired
    private HealingService healingService;
    @Autowired
    private MagicService magicService;

    public void setStartingInventory(Character character) {
        Weapon startingWeapon = choose(STARTING_WEAPON_SELECTOR);
        takeWeapon(character, startingWeapon);

        choose(STARTING_SHIELD_SELECTOR).ifPresent(startingShield -> {
            takeShield(character, startingShield);
        });
        choose(STARTING_ARMOR_SELECTOR).ifPresent(startingArmor -> {
            takeArmor(character, startingArmor);
        });
    }

    public void use(Character character, Item item) {
        switch (item.getType()) {
            case FOOD:
                healingService.eat(character, item);
                magicService.eat(character, item);
                break;
            case WEAPON:
                changeWeapon(character, item);
                break;
            case SHIELD:
                changeShield(character, item);
                break;
            case ARMOR:
                changeArmor(character, item);
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
        }
    }

    public void changeShield(Character character, Item item) {
        if (!item.getType().equals(SHIELD)) {
            throw new IllegalArgumentException("Only shield can be used as shield!");
        }

        Shield newShield = SHIELDS.get(item.getCode());
        if (newShield == null) {
            throw new IllegalStateException("New shield not recognized as real shield: " + item.getCode());
        }

        if (character.getShield() == null || newShield.getPopularity() > character.getShield().getPopularity()) {
            takeShield(character, newShield);
        }
    }

    public void changeArmor(Character character, Item item) {
        if (!item.getType().equals(ARMOR)) {
            throw new IllegalArgumentException("Only armor can be used as armor!");
        }

        Armor newArmor = ARMORS.get(item.getCode());
        if (newArmor == null) {
            throw new IllegalStateException("New armor not recognized as real armor: " + item.getCode());
        }

        if (character.getArmor() == null || newArmor.getPopularity() > character.getArmor().getPopularity()) {
            takeArmor(character, newArmor);
        }
    }

    private void takeWeapon(Character character, Weapon weapon) {
        if (weapon.isOneHanded() || character.getShield() == null) {
            character.setWeapon(weapon);
            characterCalculationService.recalculateProperties(character);
            log.info("Weapon used: " + weapon.getCode());
        } else {
            log.info(weapon.getCode() + " cannot be used with a shield!");
        }
    }

    private void takeShield(Character character, Shield shield) {
        if (character.getWeapon().isOneHanded()) {
            character.setShield(shield);
            characterCalculationService.recalculateProperties(character);
            log.info("Shield used: " + shield.getCode());
        } else {
            log.info(shield.getCode() + " cannot be used with two-handed weapon!");
        }
    }

    private void takeArmor(Character character, Armor armor) {
        character.setArmor(armor);
        characterCalculationService.recalculateProperties(character);
        log.info("Armor used: " + armor.getCode());
    }
}
