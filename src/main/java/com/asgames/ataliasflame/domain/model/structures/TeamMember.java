package com.asgames.ataliasflame.domain.model.structures;

import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeamMember {

    private final Combatant combatant;
    private final int team;
}
