package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.ArmorType;
import com.asgames.ataliasflame.domain.model.enums.ItemType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArmorDto {
    protected String reference;
    protected String code;
    protected ItemType type;
    private int defense;
    private int absorption;
    private ArmorType armorType;
    private int durability;
    private int wearAndTear;
}
