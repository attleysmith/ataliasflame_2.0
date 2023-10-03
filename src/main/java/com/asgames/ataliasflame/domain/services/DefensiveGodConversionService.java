package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.God;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.Caste.MONK;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.CLERIC;

@Service
public class DefensiveGodConversionService {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public DefensiveGodConversionLog getConversionLog(Character character) {
        Caste characterCaste = character.getCaste();
        if (characterCaste.equals(MONK) || !characterCaste.group.equals(CLERIC)) {
            throw new IllegalArgumentException("Only higher rank clerics can convert characters! (At least priests.)");
        }

        return DefensiveGodConversionLog.builder()
                .conversionCode(UUID.randomUUID().toString())
                .cleric(character)
                .god(character.getDefensiveGod())
                .build();
    }

    public DefensiveGodConversionLog convert(Character character, DefensiveGodConversionLog conversionLog) {
        if (conversionLog.getConvertedCharacter() != null) {
            throw new IllegalArgumentException("Conversion code is already used!");
        }
        validateConstraints(character, conversionLog.getGod());

        character.setDefensiveGod(conversionLog.getGod());
        characterCalculationService.recalculateProperties(character);
        conversionLog.setConvertedCharacter(character);

        return conversionLog;
    }

    private void validateConstraints(Character character, God newGod) {
        if (character.getRace().prohibitedGods.contains(newGod)) {
            throw new IllegalArgumentException(character.getRace() + " cannot be a follower of " + newGod);
        }
        if (newGod.prohibitedCastes.contains(character.getCaste())) {
            throw new IllegalArgumentException(character.getCaste() + " cannot be a follower of " + newGod);
        }
    }
}
