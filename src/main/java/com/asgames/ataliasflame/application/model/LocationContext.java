package com.asgames.ataliasflame.application.model;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Location;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationContext {

    private Character character;
    private Location location;
}
