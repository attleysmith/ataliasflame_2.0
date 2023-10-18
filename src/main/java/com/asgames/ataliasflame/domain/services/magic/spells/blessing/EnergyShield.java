package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.ArmorType;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_SHIELD;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellArmorEvent.spellArmor;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class EnergyShield extends BlessingSpell {

    @Autowired
    private CharacterCalculationService characterCalculationService;

    private static final int SPELL_COST = 6;

    // armor effect
    private static final int DEFENSE = 5;
    private static final int ABSORPTION = 100;
    private static final int DURABILITY = 40;

    public EnergyShield() {
        super(ENERGY_SHIELD);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        character.getCover().getEnergyArmor()
                .ifPresentOrElse(
                        armor -> armor.getDurability().fullRecover(),
                        () -> {
                            character.getCover().set(Armor.builder()
                                    .reference(UUID.randomUUID().toString())
                                    .code(name.name())
                                    .type(ARMOR)
                                    .armorType(ArmorType.ENERGY_ARMOR)
                                    .defense(DEFENSE)
                                    .absorption(ABSORPTION)
                                    .durability(Energy.withTotal(DURABILITY))
                                    .build());
                            characterCalculationService.recalculateProperties(character);
                        }
                );
        character.getCover().getEnergyArmor().ifPresent(armor ->
                storyLineLogger.event(spellArmor(character, armor)));
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Summons an energy armor covering the caster's body. " +
                "Effect: [" + effectDetailsOf(DEFENSE, ABSORPTION, DURABILITY) + "] " +
                "The absorption effect of the energy armor precedes the effect of any other armor. " +
                "Cost: " + SPELL_COST + " MP";
    }
}
