package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.ActiveBlessing;
import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import com.asgames.ataliasflame.domain.services.CharacterCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CharacterCalculationService characterCalculationService;

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
                            Armor.builder()
                                    .reference(UUID.randomUUID().toString())
                                    .code(name.name())
                                    .type(ARMOR)
                                    .armorType(DIVINE_ARMOR)
                                    .defense(DEFENSE)
                                    .absorption(ABSORPTION)
                                    .durability(Energy.withTotal(DURABILITY))
                                    .build()
                                    .belongsTo(character);
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
}
