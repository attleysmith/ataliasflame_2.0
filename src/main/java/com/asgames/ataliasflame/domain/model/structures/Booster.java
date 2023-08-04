package com.asgames.ataliasflame.domain.model.structures;

import com.asgames.ataliasflame.domain.model.enums.Attribute;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class Booster {

    private final Map<Attribute, Integer> effects;

}
