package com.asgames.ataliasflame.domain.services;

import com.asgames.ataliasflame.domain.model.entities.Shield;
import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.services.storyline.StoryLineLogger;
import com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.CombatDamageEvent.combatDamage;
import static com.asgames.ataliasflame.domain.services.storyline.events.CombatEvents.DamageAbsorptionEvent.damageAbsorption;
import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static com.asgames.ataliasflame.domain.utils.DiceUtils.successX;

@RequiredArgsConstructor
@Service
public class DamageService {

    private final StoryLineLogger storyLineLogger;

    private static final int CRITICAL_HIT_CHANCE = 2;
    private static final int CRITICAL_HIT_DAMAGE_BONUS = 50;
    private static final int HEAD_HIT_CHANCE = 20;
    private static final int HEAD_HIT_DAMAGE_BONUS = 25;
    private static final int SHIELD_BLOCKING_PENETRATION = 55;

    public void doDamage(Combatant attacker, Combatant defender, int damage, DamageType damageType) {
        AtomicInteger remainingDamage = new AtomicInteger(damage);
        defender.getShield().filter(shield -> shield.getDurability().hasOne()).ifPresent(shield -> blocking(shield, remainingDamage));

        boolean headHit = headHitX(remainingDamage);
        boolean criticalHit = criticalHitX(remainingDamage);

        defender.getCover().getEnergyArmor().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));
        if (!criticalHit) {
            if (headHit) {
                defender.getCover().getHelmet().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));
            } else {
                defender.getCover().getBodyArmor().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));
            }
        }
        defender.getCover().getDivineArmor().filter(armor -> armor.getDurability().hasOne()).ifPresent(armor -> absorption(armor, remainingDamage));

        defender.getHealth().damage(remainingDamage.get());
        storyLineLogger.event(combatDamage(attacker, defender, remainingDamage.get(), damageType, headHit, criticalHit));
    }

    private void blocking(Shield shield, AtomicInteger damage) {
        if (successX(shield.getBlocking())) {
            damage.updateAndGet(value -> percent(value, SHIELD_BLOCKING_PENETRATION));
            absorption(shield, damage);
        }
    }

    private void absorption(AbsorptionDefense defense, AtomicInteger damage) {
        int originalDamage = damage.get();
        int absorbedDamage = percent(originalDamage, defense.getAbsorption());
        int penetration = defense.getDurability().penetrate(absorbedDamage);
        damage.set(originalDamage - absorbedDamage + penetration);
        storyLineLogger.event(damageAbsorption(defense, originalDamage, absorbedDamage, penetration));
    }

    private boolean criticalHitX(AtomicInteger damage) {
        boolean criticalHit = successX(CRITICAL_HIT_CHANCE);
        int bonusDamage = criticalHit ? percent(damage.get(), CRITICAL_HIT_DAMAGE_BONUS) : 0;
        damage.addAndGet(bonusDamage);
        return criticalHit;
    }

    private boolean headHitX(AtomicInteger damage) {
        boolean headHit = successX(HEAD_HIT_CHANCE);
        int bonusDamage = headHit ? percent(damage.get(), HEAD_HIT_DAMAGE_BONUS) : 0;
        damage.addAndGet(bonusDamage);
        return headHit;
    }
}
