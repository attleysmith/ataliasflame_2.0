package com.asgames.ataliasflame.interfaces.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class LocationDto {
    private String reference;
    private int level;
    private boolean seized;
    private List<MonsterDto> monsters;
    private List<ItemDto> items;
}
