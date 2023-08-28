package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import java.util.List;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.INFO;
import static java.util.stream.Collectors.joining;

public final class LocationEvents {

    private LocationEvents() {
    }

    public abstract static class LocationEvent extends StoryLineEvent {
        protected final Location location;

        protected LocationEvent(EventType eventType, Location location) {
            super(eventType);
            this.location = location;
        }
    }

    public static class MonstersAppearEvent extends LocationEvent {

        private MonstersAppearEvent(Location location) {
            super(INFO, location);
        }

        public static MonstersAppearEvent monstersAppear(Location location) {
            return new MonstersAppearEvent(location);
        }

        @Override
        public String message() {
            List<Monster> monsters = location.getMonsters();
            return "Monsters appeared (" + monsters.size() + ") -> " + monsters.stream().map(Monster::getCode).collect(joining(", "));
        }
    }
}
