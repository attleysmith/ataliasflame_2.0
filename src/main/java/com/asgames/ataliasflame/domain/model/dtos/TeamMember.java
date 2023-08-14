package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeamMember {

    private final Combatant combatant;
    private final int team;

    public String getCode() {
        return combatant.getCode();
    }

    public int getInitiative() {
        return combatant.getInitiative();
    }

    public boolean isDead() {
        return combatant.isDead();
    }
}
