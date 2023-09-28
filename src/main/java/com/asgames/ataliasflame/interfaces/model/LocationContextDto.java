package com.asgames.ataliasflame.interfaces.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationContextDto {
    private CharacterDto character;
    private LocationDto location;
}
