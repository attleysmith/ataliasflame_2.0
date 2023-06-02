package com.asgames.ataliasflame.application.mappers;

import com.asgames.ataliasflame.application.model.CharacterInput;
import com.asgames.ataliasflame.domain.model.entities.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    public abstract Character toCharacter(CharacterInput characterInput);
}
