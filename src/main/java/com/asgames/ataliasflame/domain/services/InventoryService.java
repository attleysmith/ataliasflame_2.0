package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.*;
import com.asgames.ataliasflame.domain.model.interfaces.ItemTemplate;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.domain.utils.SelectionValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asgames.ataliasflame.domain.model.enums.ArmorTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.BODY_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.HELMET;
import static com.asgames.ataliasflame.domain.model.enums.InventoryType.*;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.*;
import static com.asgames.ataliasflame.domain.model.enums.ShieldTemplate.*;
import static com.asgames.ataliasflame.domain.model.enums.WeaponTemplate.*;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ArmorDropEvent.armorDrop;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ArmorUseEvent.armorUse;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.EatingEvent.eating;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ShieldDropEvent.shieldDrop;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ShieldSwitchEvent.shieldSwitch;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.ShieldUseEvent.shieldUse;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.WeaponDropEvent.weaponDrop;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.WeaponSwitchEvent.weaponSwitch;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.WeaponUseEvent.weaponUse;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.choose;

@Service
public class InventoryService {

    private static final List<SelectionValue<Optional<WeaponTemplate>>> STARTING_WEAPON_SELECTOR = List.of(
            new SelectionValue<>(5, Optional.empty()),
            new SelectionValue<>(30, Optional.of(STAFF)),
            new SelectionValue<>(30, Optional.of(DAGGER)),
            new SelectionValue<>(20, Optional.of(SPEAR)),
            new SelectionValue<>(15, Optional.of(SWORD))
    );
    private static final List<SelectionValue<Optional<ShieldTemplate>>> STARTING_SHIELD_SELECTOR = List.of(
            new SelectionValue<>(60, Optional.empty()),
            new SelectionValue<>(10, Optional.of(BUCKLER)),
            new SelectionValue<>(15, Optional.of(ROUND_SHIELD)),
            new SelectionValue<>(10, Optional.of(KITE_SHIELD)),
            new SelectionValue<>(5, Optional.of(TOWER_SHIELD))
    );
    private static final List<SelectionValue<Optional<ArmorTemplate>>> STARTING_HELMET_SELECTOR = List.of(
            new SelectionValue<>(60, Optional.empty()),
            new SelectionValue<>(20, Optional.of(CAP)),
            new SelectionValue<>(10, Optional.of(LEATHER_HELMET)),
            new SelectionValue<>(5, Optional.of(CHAIN_HOOD)),
            new SelectionValue<>(5, Optional.of(METAL_HELMET))
    );
    private static final List<SelectionValue<Optional<ArmorTemplate>>> STARTING_BODY_ARMOR_SELECTOR = List.of(
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

    public Map<InventoryType, Item> getInventory(Character character) {
        Map<InventoryType, Item> characterInventory = new HashMap<>();
        character.getWeapon().ifPresent(weapon -> characterInventory.put(USED_WEAPON, weapon));
        character.getInventory().getSpareWeapon().ifPresent(weapon -> characterInventory.put(SPARE_WEAPON, weapon));
        character.getShield().ifPresent(shield -> characterInventory.put(USED_SHIELD, shield));
        character.getInventory().getSpareShield().ifPresent(shield -> characterInventory.put(SPARE_SHIELD, shield));
        character.getCover().get(HELMET).ifPresent(armor -> characterInventory.put(USED_HELMET, armor));
        character.getCover().get(BODY_ARMOR).ifPresent(armor -> characterInventory.put(USED_BODY_ARMOR, armor));
        return characterInventory;
    }

    public void setStartingInventory(Character character) {
        choose(STARTING_WEAPON_SELECTOR).ifPresent(startingWeapon ->
                takeWeapon(character, startingWeapon.instance()));

        if (character.hasFreeHand()) {
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
        return ((ShieldTemplate) template).instance().worn();
    }

    private Armor produceArmor(ItemTemplate template) {
        if (!template.getType().equals(ARMOR)) {
            throw new IllegalArgumentException("Only ARMOR can be produced as armor!");
        }
        return ((ArmorTemplate) template).instance().worn();
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

        character.setWeapon(newWeapon);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(weaponUse(character, newWeapon, USED_WEAPON));
    }

    public void storeWeapon(Character character, Weapon newWeapon) {
        dropSpareWeapon(character);

        character.getInventory().setSpareWeapon(newWeapon);
        storyLineLogger.event(weaponUse(character, newWeapon, SPARE_WEAPON));
    }

    public void dropWeapon(Character character) {
        character.getWeapon().ifPresent(oldWeapon -> {
            if (character.getLocation() != null) {
                character.getLocation().getItems().add(oldWeapon);
            }

            character.setWeapon(null);
            characterCalculationService.recalculateProperties(character);
            storyLineLogger.event(weaponDrop(character, oldWeapon, USED_WEAPON));
        });
    }

    public void dropSpareWeapon(Character character) {
        character.getInventory().getSpareWeapon().ifPresent(oldWeapon -> {
            if (character.getLocation() != null) {
                character.getLocation().getItems().add(oldWeapon);
            }

            character.getInventory().setSpareWeapon(null);
            storyLineLogger.event(weaponDrop(character, oldWeapon, SPARE_WEAPON));
        });
    }

    public void takeShield(Character character, Shield newShield) {
        dropShield(character);
        dropSpareShield(character);
        if (!character.hasFreeHand()) {
            dropWeapon(character);
        }

        character.setShield(newShield);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(shieldUse(character, newShield, USED_SHIELD));
    }

    public void storeShield(Character character, Shield newShield) {
        dropShield(character);
        dropSpareShield(character);

        character.getInventory().setSpareShield(newShield);
        storyLineLogger.event(shieldUse(character, newShield, SPARE_SHIELD));
    }

    public void dropShield(Character character) {
        character.getShield().ifPresent(oldShield -> {
            if (character.getLocation() != null) {
                character.getLocation().getItems().add(oldShield);
            }

            character.setShield(null);
            characterCalculationService.recalculateProperties(character);
            storyLineLogger.event(shieldDrop(character, oldShield, USED_SHIELD));
        });
    }

    public void dropSpareShield(Character character) {
        character.getInventory().getSpareShield().ifPresent(oldShield -> {
            if (character.getLocation() != null) {
                character.getLocation().getItems().add(oldShield);
            }

            character.getInventory().setSpareShield(null);
            storyLineLogger.event(shieldDrop(character, oldShield, SPARE_SHIELD));
        });
    }

    public void takeArmor(Character character, Armor newArmor) {
        dropArmor(character, newArmor.getArmorType());

        character.getCover().set(newArmor);
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(armorUse(character, newArmor));
    }

    public void dropArmor(Character character, ArmorType armorType) {
        switch (armorType) {
            case HELMET, BODY_ARMOR -> character.getCover().get(armorType).ifPresent(oldArmor -> {
                if (character.getLocation() != null) {
                    character.getLocation().getItems().add(oldArmor);
                }

                character.getCover().drop(armorType);
                characterCalculationService.recalculateProperties(character);
                storyLineLogger.event(armorDrop(character, oldArmor));
            });
            case ENERGY_ARMOR, DIVINE_ARMOR ->
                    throw new IllegalArgumentException("Dropped armor is not part of the inventory: " + armorType);
        }
    }

    public void switchWeapons(Character character) {
        Optional<Weapon> weapon = character.getWeapon();
        Optional<Weapon> spareWeapon = character.getInventory().getSpareWeapon();

        weapon.ifPresentOrElse(weaponToStore -> {
                    character.setWeapon(null);
                    character.getInventory().setSpareWeapon(weaponToStore);
                },
                () -> character.getInventory().setSpareWeapon(null));
        spareWeapon.ifPresent(weaponToUse -> {
            if (weaponToUse.isOneHanded()) {
                character.getInventory().getSpareShield().ifPresent(shield -> switchShields(character));
            } else {
                character.getShield().ifPresent(shield -> switchShields(character));
            }
            character.setWeapon(weaponToUse);
        });
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(weaponSwitch(character));
    }

    public void switchShields(Character character) {
        Optional<Shield> shield = character.getShield();
        Optional<Shield> spareShield = character.getInventory().getSpareShield();
        shield.ifPresentOrElse(shieldToStore -> {
                    character.setShield(null);
                    character.getInventory().setSpareShield(shieldToStore);
                },
                () -> character.getInventory().setSpareShield(null));
        spareShield.ifPresent(shieldToUse -> {
            if (!character.hasFreeHand()) {
                throw new IllegalStateException("There must be a free hand to use shield!");
            }
            character.setShield(shieldToUse);
        });
        characterCalculationService.recalculateProperties(character);
        storyLineLogger.event(shieldSwitch(character));
    }
}
