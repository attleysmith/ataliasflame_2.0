package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.entities.Companion;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

public final class CompanionEvents {

    private CompanionEvents() {
    }

    public abstract static class CompanionEvent extends StoryLineEvent {
        protected final Companion companion;

        protected CompanionEvent(EventType eventType, Companion companion) {
            super(eventType);
            this.companion = companion;
        }
    }

    public static class CompanionSummoningEvent extends CompanionEvent {

        private CompanionSummoningEvent(Companion companion) {
            super(INFO, companion);
        }

        public static CompanionSummoningEvent summoning(Companion companion) {
            return new CompanionSummoningEvent(companion);
        }

        @Override
        public String message() {
            return companion.getName() + " (" + companion.getReference() + ") summoned as companion.";
        }
    }

    public static class CompanionHealingEvent extends CompanionEvent {
        private final int oldHealth;

        private CompanionHealingEvent(Companion companion, int oldHealth) {
            super(INFO, companion);
            this.oldHealth = oldHealth;
        }

        public static CompanionHealingEvent companionHealing(Companion companion, int oldHealth) {
            return new CompanionHealingEvent(companion, oldHealth);
        }

        @Override
        public String message() {
            return "Healing " + companion.getName() + " (" + companion.getReference() + "): "
                    + oldHealth + " -> " + companion.getHealth().actualValue();
        }
    }
}
