package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.Booster;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ActiveBlessingDto {
    private String reference;
    private Booster booster;
    private String sourceSoulChip;
}
