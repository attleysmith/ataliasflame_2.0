package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeamMember {

    private final int team;
    private final Combatant combatant;
    private TeamMember swornEnemy;

    public String getReference() {
        return combatant.getReference();
    }

    public int getInitiative() {
        return combatant.getInitiative();
    }

    public boolean isAlive() {
        return combatant.isAlive();
    }

    public boolean isDead() {
        return combatant.isDead();
    }
}
