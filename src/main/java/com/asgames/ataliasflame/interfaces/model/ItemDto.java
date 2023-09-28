package com.asgames.ataliasflame.interfaces.model;

import com.asgames.ataliasflame.domain.model.enums.ItemType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDto {
    protected String reference;
    protected String code;
    protected ItemType type;
}
