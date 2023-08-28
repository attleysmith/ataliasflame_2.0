package com.asgames.ataliasflame.domain.services.storyline.events;

import com.asgames.ataliasflame.domain.services.storyline.EventType;
import lombok.Getter;

public abstract class StoryLineEvent {
    @Getter
    protected final EventType eventType;

    public StoryLineEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public abstract String message();
}
