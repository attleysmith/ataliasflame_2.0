package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.services.magic.spells.EnergySpell;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.ENERGY_BLOCKING;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;

@Component
public class EnergyBlocking extends CurseSpell implements EnergySpell {

    public EnergyBlocking() {
        super(ENERGY_BLOCKING);
    }

    @Override
    public void enforce(Character character, @Nullable Monster targetMonster, Map<String, String> args) {
        EnergyArgs energyArgs = new EnergyArgs(args);

        int investedEnergy = percent(character.getMagic().totalValue(), energyArgs.energyPercentage);
        character.getMagic().use(investedEnergy);
        storyLineLogger.event(spellCasting(character, this));

        character.getLocation().getMonsters().forEach(monster ->
                blockEnergy(monster, energyArgs.energyPercentage));
    }

    private void blockEnergy(Monster monster, int percentage) {
        int oldAttack = monster.getAttack();
        int oldDefense = monster.getDefense();
        int oldMinDamage = monster.getMinDamage();
        int oldMaxDamage = monster.getMaxDamage();
        int oldHealth = monster.getHealth().totalValue();

        monster.setAttack(calculate(oldAttack, -1 * percentage));
        monster.setDefense(calculate(oldDefense, -1 * percentage));
        monster.setMinDamage(calculate(oldMinDamage, -1 * percentage));
        monster.setMaxDamage(calculate(oldMaxDamage, -1 * percentage));

        storyLineLogger.event(curseCasting(monster, this, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getDetails() {
        return "Lowering the attributes of the enemies on the location by blocking their energies. " +
                "Effect: lowers attack, defense and damage by the invested 'energy' percentage" +
                "Cost: calculated from the invested 'energy' percentage";
    }

    @Override
    public void validateArgs(Map<String, String> args) {
        EnergyArgs.validateArgs(args);
    }
}
