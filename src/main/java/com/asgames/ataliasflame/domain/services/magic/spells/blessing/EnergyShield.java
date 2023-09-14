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
import static com.asgames.ataliasflame.domain.model.enums.ArmorType.ENERGY;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_SHIELD;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class EnergyShield extends SpellEffect {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    private static final int DEFENSE = 5;
    private static final int ABSORPTION = 100;
    private static final int DURABILITY = 40;

    private final Spell spell = SPELLS.get(spellName);

    public EnergyShield() {
        super(ENERGY_SHIELD);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(spell.getCost());
        storyLineLogger.event(spellCasting(character, spell));

        character.getArmors().stream()
                .filter(armor -> armor.getArmorType().equals(ENERGY))
                .findAny()
                .ifPresentOrElse(
                        armor -> armor.getDurability().fullRecover(),
                        () -> Armor.builder()
                                .reference(UUID.randomUUID().toString())
                                .code(spellName.name())
                                .type(ARMOR)
                                .armorType(ENERGY)
                                .defense(DEFENSE)
                                .absorption(ABSORPTION)
                                .durability(Energy.withTotal(DURABILITY))
                                .build()
                                .belongsTo(character)
                );
        storyLineLogger.event(blessing(character, spellName.name()));
    }
}
