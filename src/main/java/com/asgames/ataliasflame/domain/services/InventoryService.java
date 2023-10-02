package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.*;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.ArmorTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.model.enums.ShieldTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.WeaponTemplate.*;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ArmorChangeEvent.armorChange;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.EatingEvent.eating;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ShieldChangeEvent.shieldChange;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.WeaponChangeEvent.weaponChange;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Service
public class InventoryService {

    private static final List<SelectionValue<WeaponTemplate>> STARTING_WEAPON_SELECTOR = List.of(
            new SelectionValue<>(5, FIST),
            new SelectionValue<>(30, STAFF),
            new SelectionValue<>(30, DAGGER),
            new SelectionValue<>(20, SPEAR),
            new SelectionValue<>(15, SWORD)
    );
    private static final List<SelectionValue<Optional<ShieldTemplate>>> STARTING_SHIELD_SELECTOR = List.of(
            new SelectionValue<>(60, Optional.empty()),
            new SelectionValue<>(10, Optional.of(BUCKLER)),
            new SelectionValue<>(15, Optional.of(ROUND_SHIELD)),
            new SelectionValue<>(10, Optional.of(KITE_SHIELD)),
            new SelectionValue<>(5, Optional.of(TOWER_SHIELD))
    );
    public static final List<SelectionValue<Optional<ArmorTemplate>>> STARTING_HELMET_SELECTOR = List.of(
            new SelectionValue<>(60, Optional.empty()),
            new SelectionValue<>(20, Optional.of(CAP)),
            new SelectionValue<>(10, Optional.of(LEATHER_HELMET)),
            new SelectionValue<>(5, Optional.of(CHAIN_HOOD)),
            new SelectionValue<>(5, Optional.of(METAL_HELMET))
    );
    public static final List<SelectionValue<Optional<ArmorTemplate>>> STARTING_BODY_ARMOR_SELECTOR = List.of(
            new SelectionValue<>(50, Optional.empty()),
            new SelectionValue<>(15, Optional.of(LINEN_ARMOR)),
            new SelectionValue<>(10, Optional.of(LEATHER_ARMOR)),
            new SelectionValue<>(10, Optional.of(STUDDED_LEATHER_ARMOR)),
            new SelectionValue<>(5, Optional.of(CHAIN_MAIL)),
            new SelectionValue<>(5, Optional.of(PLATE_MAIL)),
            new SelectionValue<>(5, Optional.of(FULL_PLATE_MAIL))
    );

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

        if (character.getWeapon().isOneHanded()) {
            choose(STARTING_SHIELD_SELECTOR).ifPresent(startingShield ->
                    takeShield(character, startingShield.instance()));
        }

        choose(STARTING_HELMET_SELECTOR).ifPresent(startingArmor ->
                takeArmor(character, startingArmor.instance()));
        choose(STARTING_BODY_ARMOR_SELECTOR).ifPresent(startingArmor ->
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
        return ((FoodTemplate) template).instance();
    }

    private Weapon produceWeapon(ItemTemplate template) {
        if (!template.getType().equals(WEAPON)) {
            throw new IllegalArgumentException("Only WEAPON can be produced as weapon!");
        }
        return ((WeaponTemplate) template).instance();
    }

    private Shield produceShield(ItemTemplate template) {
        if (!template.getType().equals(SHIELD)) {
            throw new IllegalArgumentException("Only SHIELD can be produced as shield!");
        }
        return ((ShieldTemplate) template).instance().butDamaged();
    }

    private Armor produceArmor(ItemTemplate template) {
        if (!template.getType().equals(ARMOR)) {
            throw new IllegalArgumentException("Only ARMOR can be produced as armor!");
        }
        return ((ArmorTemplate) template).instance().butDamaged();
    }

    public void eatFood(Character character, Food food) {
        storyLineLogger.event(eating(character, food));
        healingService.eat(character, food);
        magicService.eat(character, food);
    }

    public void takeWeapon(Character character, Weapon newWeapon) {
        dropWeapon(character);
        if (!newWeapon.isOneHanded()) {
            dropShield(character);
        }

        Weapon oldWeapon = character.getWeapon();
        newWeapon.belongsTo(character);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(weaponChange(character, oldWeapon));
    }

    public void takeShield(Character character, Shield newShield) {
        dropShield(character);
        if (!character.getWeapon().isOneHanded()) {
            dropWeapon(character);
        }

        Shield oldShield = character.getShield().orElse(null);
        newShield.belongsTo(character);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(shieldChange(character, oldShield));
    }

    public void takeArmor(Character character, Armor newArmor) {
        dropArmor(character, newArmor.getArmorType());

        Armor oldArmor = character.getCover().get(newArmor.getArmorType()).orElse(null);
        newArmor.belongsTo(character);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(armorChange(character, oldArmor, newArmor));
    }

    public void dropWeapon(Character character) {
        if (character.getWeapon() == null) {
            return;
        }
        Weapon oldWeapon = character.getWeapon();
        if (character.getLocation() != null && !oldWeapon.getCode().equals(FIST.name())) {
            character.getLocation().getItems().add(oldWeapon);
        }
        Weapon newWeapon = FIST.instance();
        newWeapon.belongsTo(character);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(weaponChange(character, oldWeapon));
    }

    public void dropShield(Character character) {
        character.getShield().ifPresent(oldShield -> {
            if (character.getLocation() != null) {
                character.getLocation().getItems().add(oldShield);
            }

            character.setShield(null);
            characterCalculationService.recalculateProperties(character);
            storyLineLogger.event(shieldChange(character, oldShield));
        });
    }

    public void dropArmor(Character character, ArmorType armorType) {
        switch (armorType) {
            case HELMET, BODY_ARMOR -> character.getCover().get(armorType).ifPresent(oldArmor -> {
                if (character.getLocation() != null) {
                    character.getLocation().getItems().add(oldArmor);
                }

                character.getCover().drop(armorType);
                characterCalculationService.recalculateProperties(character);
                storyLineLogger.event(armorChange(character, oldArmor, null));
            });
            case ENERGY_ARMOR, DIVINE_ARMOR ->
                    throw new IllegalArgumentException("Dropped armor is not part of the inventory: " + armorType);
        }


    }
}
