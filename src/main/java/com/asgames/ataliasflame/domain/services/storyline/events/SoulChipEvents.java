package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.entities.SoulChip;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;
import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;

public final class SoulChipEvents {

    private SoulChipEvents() {
    }

    public abstract static class SoulChipEvent extends StoryLineEvent {
        protected final SoulChip soulChip;

        protected SoulChipEvent(EventType eventType, SoulChip soulChip) {
            super(eventType);
            this.soulChip = soulChip;
        }
    }

    public static class NewSoulChipEvent extends SoulChipEvent {

        private NewSoulChipEvent(SoulChip soulChip) {
            super(INFO, soulChip);
        }

        public static NewSoulChipEvent newSoulChip(SoulChip soulChip) {
            return new NewSoulChipEvent(soulChip);
        }

        @Override
        public String message() {
            return "Ripping out a soul chip (" + soulChip.getUpgradePercent() + "%) -> " + soulChip.getName()
                    + " (HP: " + soulChip.getHealth() + ")";
        }
    }

    public static class SoulChipUpgradeEvent extends SoulChipEvent {
        private final int oldHealth;

        private SoulChipUpgradeEvent(SoulChip soulChip, int oldHealth) {
            super(DEBUG, soulChip);
            this.oldHealth = oldHealth;
        }

        public static SoulChipUpgradeEvent soulChipUpgrade(SoulChip soulChip, int oldHealth) {
            return new SoulChipUpgradeEvent(soulChip, oldHealth);
        }

        @Override
        public String message() {
            return "Soul chip (" + soulChip.getName() + ") upgraded. Health: " + oldHealth + " -> " + soulChip.getHealth();
        }
    }
}
