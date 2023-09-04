package com.asgames.ataliasflame.domain.model.dtos;

import com.asgames.ataliasflame.domain.model.enums.ItemType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemTemplate {

    private final String code;
    private final ItemType type;
}
