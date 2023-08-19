package com.asgames.ataliasflame.application.model;

import com.asgames.ataliasflame.domain.model.enums.God;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DefensiveGodConversionCode {

    private String code;
    private String clericReference;
    private String clericName;
    private God god;
}
