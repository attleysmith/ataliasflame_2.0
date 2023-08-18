package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.ArmorTemplate;
import com.asgames.ataliasflame.domain.model.dtos.Item;
import com.asgames.ataliasflame.domain.model.dtos.ShieldTemplate;
import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.vos.Weapon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;

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

        choose(STARTING_SHIELD_SELECTOR).ifPresent(startingShield ->
                takeShield(character, startingShield.instance()));
        choose(STARTING_ARMOR_SELECTOR).ifPresent(startingArmor ->
                takeArmor(character, startingArmor.instance()));
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

        ShieldTemplate newShieldTemplate = SHIELDS.get(item.getCode());
        if (newShieldTemplate == null) {
            throw new IllegalStateException("New shield not recognized as real shield: " + item.getCode());
        }

        Shield newShield = newShieldTemplate.instance();
        newShield.getDurability().trauma(roll100());
        if (character.getShield().isEmpty() || newShield.isBetterThan(character.getShield().get())) {
            takeShield(character, newShield);
        }
    }

    public void changeArmor(Character character, Item item) {
        if (!item.getType().equals(ARMOR)) {
            throw new IllegalArgumentException("Only armor can be used as armor!");
        }

        ArmorTemplate newArmorTemplate = ARMORS.get(item.getCode());
        if (newArmorTemplate == null) {
            throw new IllegalStateException("New armor not recognized as real armor: " + item.getCode());
        }

        Armor newArmor = newArmorTemplate.instance();
        newArmor.getDurability().trauma(roll100());
        if (character.getArmor().isEmpty() || newArmor.isBetterThan(character.getArmor().get())) {
            takeArmor(character, newArmor);
        }
    }

    private void takeWeapon(Character character, Weapon weapon) {
        if (weapon.isOneHanded() || character.getShield().isEmpty()) {
            character.setWeapon(weapon);
            characterCalculationService.recalculateProperties(character);
            log.info("Weapon used: " + weapon.getCode());
        } else {
            log.info(weapon.getCode() + " cannot be used with a shield!");
        }
    }

    private void takeShield(Character character, Shield shield) {
        if (character.getWeapon().isOneHanded()) {
            shield.setOwner(character);
            character.setShield(shield);
            characterCalculationService.recalculateProperties(character);
            log.info("Shield used: " + shield.getCode());
        } else {
            log.info(shield.getCode() + " cannot be used with two-handed weapon!");
        }
    }

    private void takeArmor(Character character, Armor armor) {
        armor.setOwner(character);
        character.setArmor(armor);
        characterCalculationService.recalculateProperties(character);
        log.info("Armor used: " + armor.getCode());
    }
}
