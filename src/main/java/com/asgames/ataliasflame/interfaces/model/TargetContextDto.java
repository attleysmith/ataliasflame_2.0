package com.asgames.ataliasflame.interfaces.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TargetContextDto {
    private CharacterDto character;
    private MonsterDto monster;
}
