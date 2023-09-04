package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.dtos.*;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.asgames.ataliasflame.domain.MockConstants.*;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ArmorChangeEvent.newArmor;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.EatingEvent.eating;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ShieldChangeEvent.newShield;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.WeaponChangeEvent.newWeapon;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;

@Service
public class InventoryService {

    @Autowired
    private StoryLineLogger storyLineLogger;

    @Autowired
    private CharacterCalculationService characterCalculationService;
    @Autowired
    private HealingService healingService;
    @Autowired
    private MagicService magicService;

    public void setStartingInventory(Character character) {
        takeWeapon(character, choose(STARTING_WEAPON_SELECTOR).instance());

        choose(STARTING_SHIELD_SELECTOR).ifPresent(startingShield ->
                takeShield(character, startingShield.instance()));
        choose(STARTING_ARMOR_SELECTOR).ifPresent(startingArmor ->
                takeArmor(character, startingArmor.instance()));
    }

    public Item produce(ItemTemplate drop) {
        return switch (drop.getType()) {
            case FOOD -> produceFood(drop);
            case WEAPON -> produceWeapon(drop);
            case SHIELD -> produceShield(drop);
            case ARMOR -> produceArmor(drop);
            default -> throw new UnsupportedOperationException("Not supported item usage: " + drop.getType());
        };
    }

    private Food produceFood(ItemTemplate template) {
        if (!template.getType().equals(FOOD)) {
            throw new IllegalArgumentException("Only FOOD can be produced as food!");
        }

        FoodTemplate foodTemplate = FOODS.get(template.getCode());
        if (foodTemplate == null) {
            throw new IllegalStateException("Food not recognized as real food: " + template.getCode());
        }

        return foodTemplate.instance();
    }

    private Weapon produceWeapon(ItemTemplate template) {
        if (!template.getType().equals(WEAPON)) {
            throw new IllegalArgumentException("Only WEAPON can be produced as weapon!");
        }

        WeaponTemplate weaponTemplate = WEAPONS.get(template.getCode());
        if (weaponTemplate == null) {
            throw new IllegalStateException("Weapon not recognized as real weapon: " + template.getCode());
        }

        return weaponTemplate.instance();
    }

    private Shield produceShield(ItemTemplate template) {
        if (!template.getType().equals(SHIELD)) {
            throw new IllegalArgumentException("Only SHIELD can be produced as shield!");
        }

        ShieldTemplate shieldTemplate = SHIELDS.get(template.getCode());
        if (shieldTemplate == null) {
            throw new IllegalStateException("Shield not recognized as real shield: " + template.getCode());
        }

        Shield shield = shieldTemplate.instance();
        shield.getDurability().trauma(roll100());
        return shield;
    }

    private Armor produceArmor(ItemTemplate template) {
        if (!template.getType().equals(ARMOR)) {
            throw new IllegalArgumentException("Only ARMOR can be produced as armor!");
        }

        ArmorTemplate armorTemplate = ARMORS.get(template.getCode());
        if (armorTemplate == null) {
            throw new IllegalStateException("Armor not recognized as real armor: " + template.getCode());
        }

        Armor armor = armorTemplate.instance();
        armor.getDurability().trauma(roll100());
        return armor;
    }

    public void eatFood(Character character, Food food) {
        storyLineLogger.event(eating(character, food));
        healingService.eat(character, food);
        magicService.eat(character, food);
    }

    public void takeWeapon(Character character, Weapon newWeapon) {
        character.getShield().ifPresent(oldShield -> {
            if (!newWeapon.isOneHanded()) {
                character.setShield(null);
                storyLineLogger.event(newShield(character, oldShield));
            }
        });

        Weapon oldWeapon = character.getWeapon();
        newWeapon.belongsTo(character);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(newWeapon(character, oldWeapon));
    }

    public void takeShield(Character character, Shield shield) {
        Weapon oldWeapon = character.getWeapon();
        if (!oldWeapon.isOneHanded()) {
            Weapon newWeapon = WEAPONS.get("FIST").instance();
            newWeapon.belongsTo(character);
            storyLineLogger.event(newWeapon(character, oldWeapon));
        }

        Shield oldShield = character.getShield().orElse(null);
        shield.belongsTo(character);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(newShield(character, oldShield));
    }

    public void takeArmor(Character character, Armor armor) {
        Armor oldArmor = character.getArmor().orElse(null);
        armor.belongsTo(character);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(newArmor(character, oldArmor));
    }
}
