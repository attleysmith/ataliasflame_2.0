package com.asgames.ataliasflame.interfaces.mappers;

import com.asgames.ataliasflame.application.model.TargetContext;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.interfaces.model.MonsterDto;
import com.asgames.ataliasflame.interfaces.model.TargetContextDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CharacterDtoMapper.class})
public abstract class MonsterDtoMapper {

    @Mapping(target = "character", source = "character")
    @Mapping(target = "monster", source = "monster")
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
