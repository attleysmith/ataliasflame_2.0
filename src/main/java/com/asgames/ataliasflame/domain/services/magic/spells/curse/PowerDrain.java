package com.asgames.ataliasflame.domain.services.magic.spells.curse;

import com.asgames.ataliasflame.domain.model.entities.ActiveBlessing;
import com.asgames.ataliasflame.domain.model.entities.Character;
import com.asgames.ataliasflame.domain.model.entities.Monster;
import com.asgames.ataliasflame.domain.model.enums.Booster;
import org.springframework.stereotype.Component;

import static com.asgames.ataliasflame.domain.model.enums.SpellName.POWER_DRAIN;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.BlessingEvent.blessing;
import static com.asgames.ataliasflame.domain.services.storyline.events.CharacterEvents.SpellCastingEvent.spellCasting;
import static com.asgames.ataliasflame.domain.services.storyline.events.MonsterEvents.CurseCastingEvent.curseCasting;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.calculate;

@Component
public class PowerDrain extends CurseSpell {

    private static final int SPELL_COST = 10;

    // buff effect
    private static final Booster BOOSTER = Booster.POWER_DRAIN;

    // debuff effect
    private static final int ATTACK_MULTIPLIER = -8;
    private static final int DEFENSE_MULTIPLIER = -8;
    private static final int DAMAGE_MULTIPLIER = -8;
    private static final int HEALTH_MULTIPLIER = -1;

    public PowerDrain() {
        super(POWER_DRAIN);
    }

    @Override
    public void enforce(Character character, Monster targetMonster) {
        character.getMagic().use(SPELL_COST);
        storyLineLogger.event(spellCasting(character, this));

        if (targetMonster.isAlive()) {
            int oldAttack = targetMonster.getAttack();
            int oldDefense = targetMonster.getDefense();
            int oldMinDamage = targetMonster.getMinDamage();
            int oldMaxDamage = targetMonster.getMaxDamage();
            int oldHealth = targetMonster.getHealth().totalValue();

            targetMonster.setAttack(calculate(oldAttack, ATTACK_MULTIPLIER));
            targetMonster.setDefense(calculate(oldDefense, DEFENSE_MULTIPLIER));
            targetMonster.setMinDamage(calculate(oldMinDamage, DAMAGE_MULTIPLIER));
            targetMonster.setMaxDamage(calculate(oldMaxDamage, DAMAGE_MULTIPLIER));
            targetMonster.getHealth().set(calculate(oldHealth, HEALTH_MULTIPLIER));

            storyLineLogger.event(curseCasting(targetMonster, this, oldAttack, oldDefense, oldMinDamage, oldMaxDamage, oldHealth));

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
    }

    @Override
    public int getCost() {
        return SPELL_COST;
    }

    @Override
    public String getDetails() {
        return "Lowering the attributes of a targeted enemy by draining their energies. " +
                "Effect: [" + effectDetailsOf(ATTACK_MULTIPLIER, DEFENSE_MULTIPLIER, DAMAGE_MULTIPLIER, HEALTH_MULTIPLIER) + "] " +
                "Additionally the drained and absorbed power improves the attributes of the caster. " +
                "Effect: [" + effectDetailsOf(BOOSTER) + "] " +
                "Cost: " + SPELL_COST + " MP";
    }
}
