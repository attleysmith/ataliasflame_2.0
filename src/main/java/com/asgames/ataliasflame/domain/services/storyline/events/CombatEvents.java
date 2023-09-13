package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType.DIRECT;

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

        public enum DamageType {
            DIRECT, AREA, BLAST, CHAINING, CROSSFIRE, NOVA
        }

        private final int damage;
        private final DamageType damageType;

        private CombatDamageEvent(Combatant attacker, Combatant defender, int damage, DamageType damageType) {
            super(DEBUG, attacker, defender);
            this.damage = damage;
            this.damageType = damageType;
        }

        public static CombatDamageEvent combatDamage(Combatant attacker, Combatant defender, int damage, DamageType damageType) {
            return new CombatDamageEvent(attacker, defender, damage, damageType);
        }

        public static CombatDamageEvent combatDamage(Combatant attacker, Combatant defender, int damage) {
            return new CombatDamageEvent(attacker, defender, damage, DIRECT);
        }

        @Override
        public String message() {
            return "-- " + attacker.getCode() +
                    " (" + attacker.shortRef() + ") deals " + damage + " " + damageType + " damage to " + defender.getCode() +
                    " (" + defender.shortRef() + ") | Remaining health: " + defender.getHealth().actualValue();
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
                    " (" + attacker.shortRef() + ") missed " + defender.getCode() +
                    " (" + defender.shortRef() + ") | Remaining health: " + defender.getHealth().actualValue();
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
