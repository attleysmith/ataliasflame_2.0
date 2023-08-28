package com.asgames.ataliasflame.domain.services.storyline.events;

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
}
