package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.storyline.EventType;

import static com.asgames.ataliasflame.domain.services.storyline.EventType.DEBUG;

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
            return monster.getCode() + " dropped " + item.getCode();
        }
    }
}
