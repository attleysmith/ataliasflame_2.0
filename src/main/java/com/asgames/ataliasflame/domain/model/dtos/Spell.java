package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.model.enums.SummonType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Spell {

    private final SpellName name;
    private final MagicType type;
    private final Integer cost;
    private final int minDamage;
    private final int maxDamage;
    private final SummonType summonType;
}
