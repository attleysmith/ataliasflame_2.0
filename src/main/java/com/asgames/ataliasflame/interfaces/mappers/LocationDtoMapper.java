package com.asgames.ataliasflame.interfaces.mappers;

import com.asgames.ataliasflame.application.model.LocationContext;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.interfaces.model.ItemDto;
import com.asgames.ataliasflame.interfaces.model.LocationContextDto;
import com.asgames.ataliasflame.interfaces.model.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CharacterDtoMapper.class, MonsterDtoMapper.class})
public abstract class LocationDtoMapper {

    @Mapping(target = "location", source = "location")
    @Mapping(target = "character", source = "character")
    public abstract LocationContextDto toLocationContextDto(LocationContext locationContext);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "monsters", source = "monsters")
    @Mapping(target = "items", source = "items")
    public abstract LocationDto toLocationDto(Location location);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "type", source = "type")
    public abstract ItemDto toItemDto(Item item);
}
