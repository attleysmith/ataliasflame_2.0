package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;

public final class CombatEvents {

    private CombatEvents() {
    }

    public abstract static class CombatEvent extends StoryLineEvent {
        protected final Combatant attacker;
        protected final Combatant defender;

        protected CombatEvent(EventType eventType, Combatant attacker, Combatant defender) {
            super(eventType);
            this.attacker = attacker;
            this.defender = defender;
        }
    }

    public static class CombatDamageEvent extends CombatEvent {
        private final int damage;

        private CombatDamageEvent(Combatant attacker, Combatant defender, int damage) {
            super(DEBUG, attacker, defender);
            this.damage = damage;
        }

        public static CombatDamageEvent combatDamage(Combatant attacker, Combatant defender, int damage) {
            return new CombatDamageEvent(attacker, defender, damage);
        }

        @Override
        public String message() {
            return "-- " + attacker.getCode() +
                    " (" + attacker.getReference() + ") deals " + damage + " damage to " + defender.getCode() +
                    " (" + defender.getReference() + ") | Remaining health: " + defender.getHealth().actualValue();
        }
    }

    public static class MissedAttackEvent extends CombatEvent {

        private MissedAttackEvent(Combatant attacker, Combatant defender) {
            super(DEBUG, attacker, defender);
        }

        public static MissedAttackEvent missedAttack(Combatant attacker, Combatant defender) {
            return new MissedAttackEvent(attacker, defender);
        }

        @Override
        public String message() {
            return "-- " + attacker.getCode() +
                    " (" + attacker.getReference() + ") missed " + defender.getCode() +
                    " (" + defender.getReference() + ") | Remaining health: " + defender.getHealth().actualValue();
        }
    }

    public static class DamageAbsorptionEvent extends StoryLineEvent {
        private final AbsorptionDefense defense;
        private final int originalDamage;
        private final int absorbedDamage;
        private final int penetration;

        private DamageAbsorptionEvent(AbsorptionDefense defense, int originalDamage, int absorbedDamage, int penetration) {
            super(DEBUG);
            this.defense = defense;
            this.originalDamage = originalDamage;
            this.absorbedDamage = absorbedDamage;
            this.penetration = penetration;
        }

        public static DamageAbsorptionEvent damageAbsorption(AbsorptionDefense defense, int originalDamage, int absorbedDamage, int penetration) {
            return new DamageAbsorptionEvent(defense, originalDamage, absorbedDamage, penetration);
        }

        @Override
        public String message() {
            return "-- " + defense.getCode() +
                    " absorbed " + absorbedDamage + " damage from " + originalDamage +
                    " with " + penetration + " penetration. | Remaining defense: " + defense.getDurability().actualValue();
        }
    }
}
