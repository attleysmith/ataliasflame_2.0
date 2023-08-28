package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.WARN;

public class SimpleEvents {

    private SimpleEvents() {
    }

    public abstract static class SimpleEvent extends StoryLineEvent {

        protected SimpleEvent(EventType eventType) {
            super(eventType);
        }
    }

    public static class DebugEvent extends SimpleEvent {

        public enum DebugReportCause {
            ELIMINATED_TEAM, DEAD_ATTACKER, COMBAT_START
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
            String message;
            switch (cause) {
                case ELIMINATED_TEAM:
                    message = "Stop fighting. One of the teams is eliminated.";
                    break;
                case DEAD_ATTACKER:
                    message = "Skipping attack. Attacker is already dead.";
                    break;
                case COMBAT_START:
                    message = "Combat started.";
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown debug report cause!");
            }
            return message;
        }
    }

    public static class WarningEvent extends SimpleEvent {

        public enum WarningReportCause {
            OCCUPIED_SOULS, UNSUCCESSFUL_SUMMON, UNNECESSARY_SPELL_ATTACK, WEAPON_SHIELD_MISMATCH, NO_ENEMY
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
            String message;
            switch (cause) {
                case OCCUPIED_SOULS:
                    message = "Soul chips are occupied!";
                    break;
                case UNSUCCESSFUL_SUMMON:
                    message = "Summoning was unsuccessful!";
                    break;
                case UNNECESSARY_SPELL_ATTACK:
                    message = "Unnecessary use of attack spell!";
                    break;
                case WEAPON_SHIELD_MISMATCH:
                    message = "Shield cannot be used with a two-handed weapon!";
                    break;
                case NO_ENEMY:
                    message = "Combat without an enemy.";
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown warning report cause!");
            }
            return message;
        }
    }
}
