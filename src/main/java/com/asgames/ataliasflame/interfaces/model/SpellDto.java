package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.MagicType;
import com.asgames.ataliasflame.domain.model.enums.SpellGroup;
import com.asgames.ataliasflame.domain.model.enums.SpellName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SpellDto {
    private final SpellName name;
    private final SpellGroup group;
    private final MagicType type;
    private final int cost;
}
