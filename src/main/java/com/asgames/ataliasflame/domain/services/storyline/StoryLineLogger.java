package com.asgames.ataliasflame.domain.services.storyline;

import com.asgames.ataliasflame.domain.services.storyline.events.StoryLineEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoryLineLogger {

    public void event(StoryLineEvent event) {
        event(event.getEventType(), event.message());
    }

    private void event(EventType eventType, String text) {
        switch (eventType) {
            case INFO:
                log.info(text);
                break;
            case DEBUG:
                log.debug(text);
                break;
            case WARN:
                log.warn(text);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported event type: " + eventType);
        }
    }
}
