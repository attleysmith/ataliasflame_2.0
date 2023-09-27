package com.asgames.ataliasflame.interfaces.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoverDto {
    private ArmorDto helmet;
    private ArmorDto bodyArmor;
    private ArmorDto energyArmor;
    private ArmorDto divineArmor;
}
