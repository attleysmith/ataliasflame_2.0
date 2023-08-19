package com.asgames.ataliasflame.application.mappers;

import com.asgames.ataliasflame.application.model.DefensiveGodConversionCode;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DefensiveGodConversionCodeMapper {

    @Mapping(target = "code", source = "conversionCode")
    @Mapping(target = "clericReference", source = "cleric.reference")
    @Mapping(target = "clericName", source = "cleric.name")
    @Mapping(target = "god", source = "god")
    public abstract DefensiveGodConversionCode toCode(DefensiveGodConversionLog conversionLog);
}
