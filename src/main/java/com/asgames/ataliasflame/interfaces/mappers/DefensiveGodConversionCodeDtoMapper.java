package com.asgames.ataliasflame.interfaces.mappers;

import com.asgames.ataliasflame.application.model.DefensiveGodConversionCode;
import com.asgames.ataliasflame.interfaces.model.DefensiveGodConversionCodeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DefensiveGodConversionCodeDtoMapper {

    public abstract DefensiveGodConversionCodeDto toDto(DefensiveGodConversionCode defensiveGodConversionCode);
}
