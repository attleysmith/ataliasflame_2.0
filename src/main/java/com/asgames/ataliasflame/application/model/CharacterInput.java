package com.asgames.ataliasflame.application.model;

import com.asgames.ataliasflame.domain.model.enums.Gender;
import com.asgames.ataliasflame.domain.model.enums.God;
import com.asgames.ataliasflame.domain.model.enums.Race;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CharacterInput {

    private String name;
    private Gender gender;
    private Race race;
    private God defensiveGod;
}
