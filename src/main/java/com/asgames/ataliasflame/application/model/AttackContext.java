package com.asgames.ataliasflame.application.model;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AttackContext {

    private Character character;
    private Monster monster;
}
