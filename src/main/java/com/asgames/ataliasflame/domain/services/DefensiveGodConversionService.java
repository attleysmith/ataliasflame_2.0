package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.structures.CasteDetails;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.CASTE_DETAILS;
import static com.asgames.ataliasflame.domain.model.enums.Caste.MONK;
import static com.asgames.ataliasflame.domain.model.enums.CasteGroup.CLERIC;

@Service
public class DefensiveGodConversionService {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    public String getConversionCode(Character character) {
        CasteDetails casteDetails = CASTE_DETAILS.get(character.getCaste());
        if (casteDetails.getCaste().equals(MONK) || !casteDetails.getGroup().equals(CLERIC)) {
            throw new IllegalArgumentException("Only higher rank clerics can convert characters! (At least priests.)");
        }
        return UUID.randomUUID().toString();
    }

    public DefensiveGodConversionLog convert(Character character, DefensiveGodConversionLog conversionLog) {
        if (conversionLog.getConvertedCharacter() != null) {
            throw new IllegalArgumentException("Conversion code is already used!");
        }
        character.setDefensiveGod(conversionLog.getGod());
        characterCalculationService.recalculateProperties(character);
        conversionLog.setConvertedCharacter(character);

        return conversionLog;
    }
}
