package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.dtos.Spell;
import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.MockConstants.SPELLS;
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.DIVINE;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.DIVINE_PROTECTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class DivineProtection extends SpellEffect {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    private static final int DEFENSE = 12;
    private static final int ABSORPTION = 0;
    private static final int DURABILITY = 0;

    private final Spell spell = SPELLS.get(spellName);

    public DivineProtection() {
        super(DIVINE_PROTECTION);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        character.getCover().getDivineArmor()
                .ifPresentOrElse(
                        armor -> armor.getDurability().fullRecover(),
                        () -> Armor.builder()
                                .reference(UUID.randomUUID().toString())
                                .code(spellName.name())
                                .type(ARMOR)
                                .armorType(DIVINE)
                                .defense(DEFENSE)
                                .absorption(ABSORPTION)
                                .durability(Energy.withTotal(DURABILITY))
                                .build()
                                .belongsTo(character)
                );

        String blessing = spellName.name();
        if (!character.getBlessings().contains(blessing)) {
            int originalHealth = character.getHealth().totalValue();
            int originalMagic = character.getMagic().totalValue();
            character.getBlessings().add(blessing);
            characterCalculationService.recalculateProperties(character);
            character.getHealth().uplift(originalHealth);
            character.getMagic().uplift(originalMagic);
        } else {
            characterCalculationService.recalculateProperties(character);
        }
        storyLineLogger.event(blessing(character, blessing));
    }
}
