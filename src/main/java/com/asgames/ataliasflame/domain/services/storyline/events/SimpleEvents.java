package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.WARN;

public final class SimpleEvents {

    private SimpleEvents() {
    }

    public abstract static class SimpleEvent extends StoryLineEvent {

        protected SimpleEvent(EventType eventType) {
            super(eventType);
        }
    }

    public static class DebugEvent extends SimpleEvent {

        public enum DebugReportCause {
            ELIMINATED_TEAM, DEAD_ATTACKER, COMBAT_START, SLEEPING, NO_ANIMAL_APPEARED, NO_GUARDIAN_WARRIOR_APPEARED, NO_DIVINE_GUARDIAN_APPEARED
        }

        private final DebugReportCause cause;

        private DebugEvent(DebugReportCause cause) {
            super(DEBUG);
            this.cause = cause;
        }

        public static DebugEvent debugReport(DebugReportCause cause) {
            return new DebugEvent(cause);
        }

        @Override
        public String message() {
            return switch (cause) {
                case ELIMINATED_TEAM -> "Stop fighting. One of the teams is eliminated.";
                case DEAD_ATTACKER -> "Skipping attack. Attacker is already dead.";
                case COMBAT_START -> "Combat started.";
                case SLEEPING -> "Sleeping.";
                case NO_ANIMAL_APPEARED -> "Animals didn't answer the call.";
                case NO_GUARDIAN_WARRIOR_APPEARED -> "No guardian warrior appeared.";
                case NO_DIVINE_GUARDIAN_APPEARED -> "None of the divine guardians appeared.";
                default -> throw new UnsupportedOperationException("Unknown debug report cause!");
            };
        }
    }

    public static class WarningEvent extends SimpleEvent {

        public enum WarningReportCause {
            OCCUPIED_SOULS, UNSUCCESSFUL_SUMMON, UNNECESSARY_SPELL_ATTACK, UNNECESSARY_CURSE_ATTACK, NO_ENEMY, DUPLICATED_BLESSING
        }

        private final WarningReportCause cause;

        private WarningEvent(WarningReportCause cause) {
            super(WARN);
            this.cause = cause;
        }

        public static WarningEvent warningReport(WarningReportCause cause) {
            return new WarningEvent(cause);
        }

        @Override
        public String message() {
            return switch (cause) {
                case OCCUPIED_SOULS -> "Soul chips are occupied!";
                case UNSUCCESSFUL_SUMMON -> "Summoning was unsuccessful!";
                case UNNECESSARY_SPELL_ATTACK -> "Unnecessary use of attack spell!";
                case UNNECESSARY_CURSE_ATTACK -> "Unnecessary use of curse spell!";
                case NO_ENEMY -> "Combat without an enemy.";
                case DUPLICATED_BLESSING -> "Blessing already used.";
                default -> throw new UnsupportedOperationException("Unknown warning report cause!");
            };
        }
    }
}
