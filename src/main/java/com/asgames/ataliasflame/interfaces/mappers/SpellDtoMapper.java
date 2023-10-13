package com.asgames.ataliasflame.interfaces.mappers;

import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import com.asgames.ataliasflame.interfaces.model.SpellDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SpellDtoMapper {

    public abstract List<SpellDto> toSpellDtoList(List<Spell> spell);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "group", source = "group")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "cost", source = "cost")
    @Mapping(target = "minDamage", source = "minDamage")
    @Mapping(target = "maxDamage", source = "maxDamage")
    public abstract SpellDto toSpellDto(Spell spell);
}
