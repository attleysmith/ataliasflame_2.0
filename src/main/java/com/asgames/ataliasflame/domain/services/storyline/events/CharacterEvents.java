package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.*;
import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

public final class CharacterEvents {

    private static final String MISSING_ITEM = "NONE";

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
            INIT, LEVEL_UP, WIN, DIED_OF_DEFEAT, DIED_OF_TRAUMA, DIED_OF_BLESSING_EXPIRY
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

    public static class WeaponChangeEvent extends CharacterEvent {
        private final Weapon oldWeapon;

        private WeaponChangeEvent(Character character, Weapon oldWeapon) {
            super(INFO, character);
            this.oldWeapon = oldWeapon;
        }

        public static WeaponChangeEvent weaponChange(Character character, Weapon oldWeapon) {
            return new WeaponChangeEvent(character, oldWeapon);
        }

        @Override
        public String message() {
            String oldWeaponCode = oldWeapon == null ? MISSING_ITEM : oldWeapon.getCode();
            return "Weapon changed: " + oldWeaponCode + " -> " + character.getWeapon().getCode();
        }
    }

    public static class ShieldChangeEvent extends CharacterEvent {
        private final Shield oldShield;

        private ShieldChangeEvent(Character character, Shield oldShield) {
            super(INFO, character);
            this.oldShield = oldShield;
        }

        public static ShieldChangeEvent shieldChange(Character character, Shield oldShield) {
            return new ShieldChangeEvent(character, oldShield);
        }

        @Override
        public String message() {
            String oldShieldCode = oldShield == null ? MISSING_ITEM : oldShield.getCode();
            int oldDurability = oldShield == null ? 0 : oldShield.getDurability().actualValue();
            if (character.getShield().isPresent()) {
                Shield shield = character.getShield().get();
                return "Shield changed: " + oldShieldCode + " (" + oldDurability + ") -> " + shield.getCode() + " (" + shield.getDurability().actualValue() + ")";
            } else {
                return "Shield dropped: " + oldShieldCode + " (" + oldDurability + ")";
            }
        }
    }

    public static class ArmorChangeEvent extends CharacterEvent {
        private final Armor oldArmor;
        private final Armor newArmor;

        private ArmorChangeEvent(Character character, Armor oldArmor, Armor newArmor) {
            super(INFO, character);
            this.oldArmor = oldArmor;
            this.newArmor = newArmor;
        }

        public static ArmorChangeEvent armorChange(Character character, Armor oldArmor, Armor newArmor) {
            return new ArmorChangeEvent(character, oldArmor, newArmor);
        }

        @Override
        public String message() {
            String oldArmorCode = oldArmor == null ? MISSING_ITEM : oldArmor.getCode();
            int oldDurability = oldArmor == null ? 0 : oldArmor.getDurability().actualValue();
            return "Armor changed: " + oldArmorCode + " (" + oldDurability + ") -> " + newArmor.getCode() + " (" + newArmor.getDurability().actualValue() + ")";
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
        private final String blessing;

        private BlessingEvent(Character character, String blessing) {
            super(INFO, character);
            this.blessing = blessing;
        }

        public static BlessingEvent blessing(Character character, String blessing) {
            return new BlessingEvent(character, blessing);
        }

        @Override
        public String message() {
            return blessing + " blessing activated.";
        }
    }
}
