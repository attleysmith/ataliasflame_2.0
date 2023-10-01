package com.asgames.ataliasflame.interfaces.mappers;

import com.asgames.ataliasflame.application.model.LocationContext;
import com.asgames.ataliasflame.application.model.TargetContext;
import com.asgames.ataliasflame.domain.model.entities.Item;
import com.asgames.ataliasflame.domain.model.entities.Location;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.interfaces.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CharacterDtoMapper.class})
public abstract class LocationDtoMapper {

    @Mapping(target = "location", source = "location")
    @Mapping(target = "character", source = "character")
    public abstract LocationContextDto toLocationContextDto(LocationContext locationContext);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "seized", source = "seized")
    @Mapping(target = "monsters", source = "monsters")
    @Mapping(target = "items", source = "items")
    public abstract LocationDto toLocationDto(Location location);

    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "type", source = "type")
    public abstract ItemDto toItemDto(Item item);

    @Mapping(target = "monster", source = "monster")
    @Mapping(target = "character", source = "character")
    public abstract TargetContextDto toTargetContextDto(TargetContext targetContext);


    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "locationReference", source = "location.reference")
    @Mapping(target = "attack", source = "attack")
    @Mapping(target = "defense", source = "defense")
    @Mapping(target = "minDamage", source = "minDamage")
    @Mapping(target = "maxDamage", source = "maxDamage")
    @Mapping(target = "initiative", source = "initiative")
    @Mapping(target = "experience", source = "experience")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "totalHealth", source = "health.totalEnergy")
    @Mapping(target = "injury", source = "health.usedEnergy")
    @Mapping(target = "totalVitality", source = "vitality.totalEnergy")
    @Mapping(target = "lostVitality", source = "vitality.usedEnergy")
    public abstract MonsterDto toMonsterDto(Monster monster);
}
