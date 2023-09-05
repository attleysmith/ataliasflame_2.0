package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

public final class MonsterEvents {

    private MonsterEvents() {
    }

    public abstract static class MonsterEvent extends StoryLineEvent {
        protected final Monster monster;

        protected MonsterEvent(EventType eventType, Monster monster) {
            super(eventType);
            this.monster = monster;
        }
    }

    public static class DropEvent extends MonsterEvent {
        private final Item item;

        private DropEvent(Monster monster, Item item) {
            super(DEBUG, monster);
            this.item = item;
        }

        public static DropEvent drop(Monster monster, Item item) {
            return new DropEvent(monster, item);
        }

        @Override
        public String message() {
            String durabilitySuffix = switch (item.getType()) {
                case SHIELD -> {
                    Shield shield = (Shield) item;
                    yield " (" + shield.getDurability().actualValue() + ")";
                }
                case ARMOR -> {
                    Armor armor = (Armor) item;
                    yield " (" + armor.getDurability().actualValue() + ")";
                }
                default -> "";
            };
            return monster.getCode() + " (" + monster.getReference() + ") dropped " + item.getCode() + durabilitySuffix;
        }
    }

    public static class CurseCastingEvent extends MonsterEvent {
        private final String curse;
        private final int oldAttack;
        private final int oldDefense;
        private final int oldMinDamage;
        private final int oldMaxDamage;
        private final int oldHealth;

        private CurseCastingEvent(Monster monster, String curse, int oldAttack, int oldDefense, int oldMinDamage, int oldMaxDamage, int oldHealth) {
            super(INFO, monster);
            this.curse = curse;
            this.oldAttack = oldAttack;
            this.oldDefense = oldDefense;
            this.oldMinDamage = oldMinDamage;
            this.oldMaxDamage = oldMaxDamage;
            this.oldHealth = oldHealth;
        }

        public static CurseCastingEvent curseCasting(Monster monster, String curse, int oldAttack, int oldDefense, int oldMinDamage, int oldMaxDamage, int oldHealth) {
            return new CurseCastingEvent(monster, curse, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth);
        }

        @Override
        public String message() {
            return curse + " casted on " + monster.getCode() +
                    " (" + monster.getReference() + ") | AP: " + oldAttack + " -> " + monster.getAttack() +
                    "; DP: " + oldDefense + " -> " + monster.getDefense() + "; HP: " + oldHealth + " -> " + monster.getHealth().actualValue() +
                    "; DMG: " + oldMinDamage + "-" + oldMaxDamage + " -> " + monster.getMinDamage() + "-" + monster.getMaxDamage();
        }
    }
}
