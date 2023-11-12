package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.InventoryType;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

public final class CharacterEvents {

    private CharacterEvents() {
    }

    public abstract static class CharacterEvent extends StoryLineEvent {
        protected final Character character;

        protected CharacterEvent(EventType eventType, Character character) {
            super(eventType);
            this.character = character;
        }
    }

    public static class CharacterReportEvent extends CharacterEvent {

        public enum CharacterReportCause {
            INIT, LEVEL_UP, MAX_LEVEL_REACHED, WIN, DIED_OF_DEFEAT, DIED_OF_TRAUMA, DIED_OF_BLESSING_EXPIRY
        }

        private final CharacterReportCause cause;

        private CharacterReportEvent(Character character, CharacterReportCause cause) {
            super(INFO, character);
            this.cause = cause;
        }

        public static CharacterReportEvent characterReport(Character character, CharacterReportCause cause) {
            return new CharacterReportEvent(character, cause);
        }

        @Override
        public String message() {
            return switch (cause) {
                case INIT -> "Character initialized: " + character;
                case LEVEL_UP ->
                        "Leveling up -> " + character.getLevel() + " | Attribute points: " + character.getAttributePoints();
                case MAX_LEVEL_REACHED -> "Max level reached (" + character.getLevel() + "). No more level-up!";
                case WIN -> "You are the winner! Remaining health: " + character.getHealth().actualValue();
                case DIED_OF_DEFEAT -> "You are defeated!";
                case DIED_OF_TRAUMA -> "You died of trauma!";
                case DIED_OF_BLESSING_EXPIRY -> "You died of an expired bless effect!";
                default -> throw new UnsupportedOperationException("Unknown character report cause!");
            };
        }
    }

    public static class NewCasteEvent extends CharacterEvent {
        private final Caste oldCaste;

        private NewCasteEvent(Character character, Caste oldCaste) {
            super(INFO, character);
            this.oldCaste = oldCaste;
        }

        public static NewCasteEvent newCaste(Character character, Caste oldCaste) {
            return new NewCasteEvent(character, oldCaste);
        }

        @Override
        public String message() {
            return "Caste upgrade: " + oldCaste + " -> " + character.getCaste();
        }
    }

    public static class ExperienceEvent extends CharacterEvent {
        private final int gainedExperience;

        private ExperienceEvent(Character character, int gainedExperience) {
            super(INFO, character);
            this.gainedExperience = gainedExperience;
        }

        public static ExperienceEvent experienceGain(Character character, int gainedExperience) {
            return new ExperienceEvent(character, gainedExperience);
        }

        @Override
        public String message() {
            return "Experience gained: " + gainedExperience + " | Total experience: " + character.getExperience();
        }
    }

    public static class AttributeUpgradeEvent extends CharacterEvent {
        private final Attribute attribute;
        private final int oldValue;

        private AttributeUpgradeEvent(Character character, Attribute attribute, int oldValue) {
            super(DEBUG, character);
            this.attribute = attribute;
            this.oldValue = oldValue;
        }

        public static AttributeUpgradeEvent attributeUpgrade(Character character, Attribute attribute, int oldValue) {
            return new AttributeUpgradeEvent(character, attribute, oldValue);
        }

        @Override
        public String message() {
            return attribute.name() + ": " + oldValue + " -> " + character.getAttributes().get(attribute);
        }
    }

    public static class EatingEvent extends CharacterEvent {
        private final Food food;

        private EatingEvent(Character character, Food food) {
            super(INFO, character);
            this.food = food;
        }

        public static EatingEvent eating(Character character, Food food) {
            return new EatingEvent(character, food);
        }

        @Override
        public String message() {
            return "Eating/Drinking " + food.getCode();
        }
    }

    public static class HealthRecoveryEvent extends CharacterEvent {
        private final int oldHealth;

        private HealthRecoveryEvent(Character character, int oldHealth) {
            super(INFO, character);
            this.oldHealth = oldHealth;
        }

        public static HealthRecoveryEvent healthRecovery(Character character, int oldHealth) {
            return new HealthRecoveryEvent(character, oldHealth);
        }

        @Override
        public String message() {
            return "Healing: " + oldHealth + " -> " + character.getHealth().actualValue();
        }
    }

    public static class MagicRecoveryEvent extends CharacterEvent {
        private final int oldMagic;

        private MagicRecoveryEvent(Character character, int oldMagic) {
            super(INFO, character);
            this.oldMagic = oldMagic;
        }

        public static MagicRecoveryEvent magicRecovery(Character character, int oldMagic) {
            return new MagicRecoveryEvent(character, oldMagic);
        }

        @Override
        public String message() {
            return "Recovering magic: " + oldMagic + " -> " + character.getMagic().actualValue();
        }
    }

    public static class WeaponUseEvent extends CharacterEvent {
        private final Weapon newWeapon;
        private final InventoryType weaponType;

        private WeaponUseEvent(Character character, Weapon newWeapon, InventoryType weaponType) {
            super(INFO, character);
            this.newWeapon = newWeapon;
            this.weaponType = weaponType;
        }

        public static WeaponUseEvent weaponUse(Character character, Weapon newWeapon, InventoryType weaponType) {
            return new WeaponUseEvent(character, newWeapon, weaponType);
        }

        @Override
        public String message() {
            return switch (weaponType) {
                case USED_WEAPON -> "Weapon used: " + newWeapon.getCode();
                case SPARE_WEAPON -> "Weapon stored: " + newWeapon.getCode();
                default ->
                        throw new IllegalArgumentException("Not supported inventory type. Type must be some weapon.");
            };
        }
    }

    public static class WeaponDropEvent extends CharacterEvent {
        private final Weapon oldWeapon;
        private final InventoryType weaponType;

        private WeaponDropEvent(Character character, Weapon oldWeapon, InventoryType weaponType) {
            super(INFO, character);
            this.oldWeapon = oldWeapon;
            this.weaponType = weaponType;
        }

        public static WeaponDropEvent weaponDrop(Character character, Weapon oldWeapon, InventoryType weaponType) {
            return new WeaponDropEvent(character, oldWeapon, weaponType);
        }

        @Override
        public String message() {
            return weaponType + " dropped: " + oldWeapon.getCode();
        }
    }

    public static class WeaponSwitchEvent extends CharacterEvent {

        protected WeaponSwitchEvent(Character character) {
            super(INFO, character);
        }

        public static WeaponSwitchEvent weaponSwitch(Character character) {
            return new WeaponSwitchEvent(character);
        }

        @Override
        public String message() {
            return "Weapons switched. Used: "
                    + character.getWeapon().map(Weapon::getCode).orElse("NONE")
                    + "; Spare: "
                    + character.getInventory().getSpareWeapon().map(Weapon::getCode).orElse("NONE");
        }
    }

    public static class ShieldUseEvent extends CharacterEvent {
        private final Shield newShield;
        private final InventoryType shieldType;

        private ShieldUseEvent(Character character, Shield newShield, InventoryType shieldType) {
            super(INFO, character);
            this.newShield = newShield;
            this.shieldType = shieldType;
        }

        public static ShieldUseEvent shieldUse(Character character, Shield newShield, InventoryType shieldType) {
            return new ShieldUseEvent(character, newShield, shieldType);
        }

        @Override
        public String message() {
            return switch (shieldType) {
                case USED_SHIELD ->
                        "Shield used: " + newShield.getCode() + " (" + newShield.getDurability().actualValue() + ")";
                case SPARE_SHIELD ->
                        "Shield stored: " + newShield.getCode() + " (" + newShield.getDurability().actualValue() + ")";
                default ->
                        throw new IllegalArgumentException("Not supported inventory type. Type must be some shield.");
            };
        }
    }

    public static class ShieldDropEvent extends CharacterEvent {
        private final Shield oldShield;
        private final InventoryType shieldType;

        private ShieldDropEvent(Character character, Shield oldShield, InventoryType shieldType) {
            super(INFO, character);
            this.oldShield = oldShield;
            this.shieldType = shieldType;
        }

        public static ShieldDropEvent shieldDrop(Character character, Shield oldShield, InventoryType shieldType) {
            return new ShieldDropEvent(character, oldShield, shieldType);
        }

        @Override
        public String message() {
            return shieldType + " dropped: " + oldShield.getCode() + " (" + oldShield.getDurability().actualValue() + ")";
        }
    }

    public static class ShieldSwitchEvent extends CharacterEvent {

        protected ShieldSwitchEvent(Character character) {
            super(INFO, character);
        }

        public static ShieldSwitchEvent shieldSwitch(Character character) {
            return new ShieldSwitchEvent(character);
        }

        @Override
        public String message() {
            return "Shields switched. Used: "
                    + character.getShield().map(Shield::getCode).orElse("NONE")
                    + "; Spare: "
                    + character.getInventory().getSpareShield().map(Shield::getCode).orElse("NONE");
        }
    }

    public static class ArmorUseEvent extends CharacterEvent {
        private final Armor newArmor;

        private ArmorUseEvent(Character character, Armor newArmor) {
            super(INFO, character);
            this.newArmor = newArmor;
        }

        public static ArmorUseEvent armorUse(Character character, Armor newArmor) {
            return new ArmorUseEvent(character, newArmor);
        }

        @Override
        public String message() {
            return "Armor used: " + newArmor.getCode() + " (" + newArmor.getDurability().actualValue() + ")";
        }
    }

    public static class ArmorDropEvent extends CharacterEvent {
        private final Armor oldArmor;

        private ArmorDropEvent(Character character, Armor oldArmor) {
            super(INFO, character);
            this.oldArmor = oldArmor;
        }

        public static ArmorDropEvent armorDrop(Character character, Armor oldArmor) {
            return new ArmorDropEvent(character, oldArmor);
        }

        @Override
        public String message() {
            return "Armor dropped: " + oldArmor.getCode() + " (" + oldArmor.getDurability().actualValue() + ")";
        }
    }

    public static class SpellCastingEvent extends CharacterEvent {
        private final Spell spell;

        private SpellCastingEvent(Character character, Spell spell) {
            super(INFO, character);
            this.spell = spell;
        }

        public static SpellCastingEvent spellCasting(Character character, Spell spell) {
            return new SpellCastingEvent(character, spell);
        }

        @Override
        public String message() {
            return spell.getType() + " spell casted: " + spell.getName() + " | Remaining magic: " + character.getMagic().actualValue();
        }
    }

    public static class BlessingEvent extends CharacterEvent {
        private final ActiveBlessing blessing;

        private BlessingEvent(Character character, ActiveBlessing blessing) {
            super(INFO, character);
            this.blessing = blessing;
        }

        public static BlessingEvent blessing(Character character, ActiveBlessing blessing) {
            return new BlessingEvent(character, blessing);
        }

        @Override
        public String message() {
            return blessing.getBooster() + " blessing activated.";
        }
    }

    public static class SpellArmorEvent extends CharacterEvent {
        private final Armor armor;

        private SpellArmorEvent(Character character, Armor armor) {
            super(INFO, character);
            this.armor = armor;
        }

        public static SpellArmorEvent spellArmor(Character character, Armor armor) {
            return new SpellArmorEvent(character, armor);
        }

        @Override
        public String message() {
            return armor.getArmorType() + " armor activated: " + armor.getCode() +
                    " (" + armor.getDurability().actualValue() + ") | DEF: " + armor.getDefense();
        }
    }
}
