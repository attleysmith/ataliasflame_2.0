package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.ItemType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WeaponDto {
    protected String reference;
    protected String code;
    protected ItemType type;
    private int minDamage;
    private int maxDamage;
    private int defense;
    private int initiative;
    private boolean oneHanded;
}
