package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Spell {

    private final SpellName name;
    private final MagicType type;
    private final SpellGroup group;
    private final Integer cost;
}
