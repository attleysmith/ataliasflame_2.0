package com.asgames.ataliasflame.domain.services.magic.spells.blessing;

import com.asgames.ataliasflame.domain.model.entities.Armor;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.ArmorType;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

import static com.asgames.ataliasflame.domain.model.enums.ItemType.ARMOR;
import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_SHIELD;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellArmorEvent.spellArmor;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.weight;

@Component
public class EnergyShield extends BlessingSpell {

    private static final String ARG_KEY_ENERGY = "energy";

    // armor effect
    private static final int DEFENSE = 0;

    public EnergyShield() {
        super(ENERGY_SHIELD);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        EnergyShieldArgs energyShieldArgs = new EnergyShieldArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), energyShieldArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        character.getCover().getEnergyArmor()
                .ifPresentOrElse(
                        armor -> meltEnergyArmor(armor, energyShieldArgs.energyPercentage, investedEnergy),
                        () -> {
                            character.getCover().set(Armor.builder()
                                    .reference(UUID.randomUUID().toString())
                                    .code(name.name())
                                    .type(ARMOR)
                                    .armorType(ArmorType.ENERGY_ARMOR)
                                    .defense(DEFENSE)
                                    .absorption(energyShieldArgs.energyPercentage)
                                    .durability(Energy.withTotal(investedEnergy))
                                    .build());
                            characterCalculationService.recalculateProperties(character);
                        }
                );
        character.getCover().getEnergyArmor().ifPresent(armor ->
                storyLineLogger.event(spellArmor(character, armor)));
    }

    private void meltEnergyArmor(Armor armor, int newAbsorption, int additionalDurability) {
        int oldAbsorption = armor.getAbsorption();
        int remainingDurability = armor.getDurability().actualValue();

        int meltedAbsorption = weight(oldAbsorption, remainingDurability, newAbsorption, additionalDurability);
        armor.setAbsorption(meltedAbsorption);

        int meltedDurability = remainingDurability + additionalDurability;
        armor.getDurability().set(meltedDurability);
        armor.getDurability().fullRecover();

    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Summons an energy armor covering the caster's body. " +
                "Absorption effect: equals to the invested 'energy' percentage" +
                "Armor durability: equals to the invested magic points" +
                "If there is a remaining effect of an older energy armor the armor effects are melted depending on the remaining and newly invested energy amounts. " +
                "The absorption effect of the energy armor precedes the effect of any other armor. " +
                "Cost: calculated from the invested 'energy' percentage";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        EnergyShieldArgs.validateArgs(args);
    }

    private static class EnergyShieldArgs {

        public final int energyPercentage;

        public EnergyShieldArgs(Map<String, String> args) {
            validateArgs(args);
            energyPercentage = Integer.parseInt(args.get(ARG_KEY_ENERGY));
        }

        public static void validateArgs(Map<String, String> args) {
            if (!args.containsKey(ARG_KEY_ENERGY)) {
                throw new IllegalArgumentException("Missing argument: " + ARG_KEY_ENERGY);
            }
            if (args.size() != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments.");
            }
            try {
                int percentage = Integer.parseInt(args.get(ARG_KEY_ENERGY));
                if (percentage < 1 || 100 < percentage) {
                    throw new IllegalArgumentException("Argument [" + ARG_KEY_ENERGY + "] must be between 1 and 100.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Argument [" + ARG_KEY_ENERGY + "] must be a number!");
            }
        }
    }
}
