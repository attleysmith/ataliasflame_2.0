package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.ActiveBlessing;
import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ArmorType.DIVINE_ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.DIVINE_PROTECTION;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellArmorEvent.spellArmor;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;

@Component
public class DivineProtection extends BlessingSpell {

    private static final int SPELL_COST = 8;

    // buff effect
    private static final Booster BOOSTER = Booster.DIVINE_PROTECTION;

    // armor effect
    private static final int DEFENSE = 12;
    private static final int ABSORPTION = 0;
    private static final int DURABILITY = 0;

    public DivineProtection() {
        super(DIVINE_PROTECTION);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        character.getCover().getDivineArmor()
                .ifPresentOrElse(
                        armor -> armor.getDurability().fullRecover(),
                        () -> {
                            character.getCover().set(Armor.builder()
                                    .reference(UUID.randomUUID().toString())
                                    .code(name.name())
                                    .type(ARMOR)
                                    .armorType(DIVINE_ARMOR)
                                    .defense(DEFENSE)
                                    .absorption(ABSORPTION)
                                    .durability(Energy.withTotal(DURABILITY))
                                    .build());
                            characterCalculationService.recalculateProperties(character);
                        }
                );
        character.getCover().getDivineArmor().ifPresent(armor ->
                storyLineLogger.event(spellArmor(character, armor)));

        if (character.getBlessings().stream()
                .noneMatch(blessing -> blessing.getBooster().equals(BOOSTER))) {
            ActiveBlessing activeBlessing = ActiveBlessing.of(character, BOOSTER);
            character.getBlessings().add(activeBlessing);

            int originalHealth = character.getHealth().totalValue();
            int originalMagic = character.getMagic().totalValue();
            characterCalculationService.recalculateProperties(character);
            character.getHealth().uplift(originalHealth);
            character.getMagic().uplift(originalMagic);

            storyLineLogger.event(blessing(character, activeBlessing));
        }
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Improves the attributes of the caster by getting the blessing of their defensive god. " +
                "Effect: [" + effectDetailsOf(BOOSTER) + "] " +
                "Additionally this blessing covers the caster's body with a divine armor. " +
                "Effect: [" + effectDetailsOf(DEFENSE, ABSORPTION, DURABILITY) + "] " +
                "The absorption effect (if any) of the divine armor is the last among armors because it protects only the living body of the caster. " +
                "Cost: " + SPELL_COST + " MP";
    }
}
