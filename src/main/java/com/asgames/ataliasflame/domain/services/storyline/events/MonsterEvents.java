package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
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
                case SHIELD, ARMOR -> " (" + ((AbsorptionDefense) item).getDurability().actualValue() + ")";
                default -> "";
            };
            return monster.getCode() + " (" + monster.shortRef() + ") dropped " + item.getCode() + durabilitySuffix;
        }
    }

    public static class CurseCastingEvent extends MonsterEvent {
        private final Spell curse;
        private final int oldAttack;
        private final int oldDefense;
        private final int oldMinDamage;
        private final int oldMaxDamage;
        private final int oldHealth;

        private CurseCastingEvent(Monster monster, Spell curse, int oldAttack, int oldDefense, int oldMinDamage, int oldMaxDamage, int oldHealth) {
            super(INFO, monster);
            this.curse = curse;
            this.oldAttack = oldAttack;
            this.oldDefense = oldDefense;
            this.oldMinDamage = oldMinDamage;
            this.oldMaxDamage = oldMaxDamage;
            this.oldHealth = oldHealth;
        }

        public static CurseCastingEvent curseCasting(Monster monster, Spell curse, int oldAttack, int oldDefense, int oldMinDamage, int oldMaxDamage, int oldHealth) {
            return new CurseCastingEvent(monster, curse, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth);
        }

        @Override
        public String message() {
            return curse.getName() + " casted on " + monster.getCode() +
                    " (" + monster.shortRef() + ") | AP: " + oldAttack + " -> " + monster.getAttack() +
                    "; DP: " + oldDefense + " -> " + monster.getDefense() + "; HP: " + oldHealth + " -> " + monster.getHealth().actualValue() +
                    "; DMG: " + oldMinDamage + "-" + oldMaxDamage + " -> " + monster.getMinDamage() + "-" + monster.getMaxDamage();
        }
    }

    public static class IntimidationEvent extends MonsterEvent {
        private final int oldAttack;
        private final int oldDefense;
        private final int oldMinDamage;
        private final int oldMaxDamage;
        private final int oldHealth;

        private IntimidationEvent(Monster monster, int oldAttack, int oldDefense, int oldMinDamage, int oldMaxDamage, int oldHealth) {
            super(INFO, monster);
            this.oldAttack = oldAttack;
            this.oldDefense = oldDefense;
            this.oldMinDamage = oldMinDamage;
            this.oldMaxDamage = oldMaxDamage;
            this.oldHealth = oldHealth;
        }

        public static IntimidationEvent intimidation(Monster monster, int oldAttack, int oldDefense, int oldMinDamage, int oldMaxDamage, int oldHealth) {
            return new IntimidationEvent(monster, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth);
        }

        @Override
        public String message() {
            return "Intimidated enemy: " + monster.getCode() +
                    " (" + monster.shortRef() + ") | AP: " + oldAttack + " -> " + monster.getAttack() +
                    "; DP: " + oldDefense + " -> " + monster.getDefense() + "; HP: " + oldHealth + " -> " + monster.getHealth().actualValue() +
                    "; DMG: " + oldMinDamage + "-" + oldMaxDamage + " -> " + monster.getMinDamage() + "-" + monster.getMaxDamage();
        }
    }
}
