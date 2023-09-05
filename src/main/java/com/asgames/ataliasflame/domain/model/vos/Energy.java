package com.asgames.ataliasflame.domain.model.vos;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.asgames.ataliasflame.domain.utils.CalculatorUtils.percent;
import static java.lang.Math.max;
import static java.lang.Math.min;

@Embeddable
@Data
public class Energy {

    // JPA needs it
    public Energy() {
    }

    private Energy(int totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private int totalEnergy;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private int usedEnergy;

    public static Energy withTotal(int totalValue) {
        return new Energy(totalValue);
    }

    public void set(int totalValue) {
        totalEnergy = totalValue;
        if (usedEnergy > totalEnergy) {
            usedEnergy = totalEnergy;
        }
    }

    public int totalValue() {
        return totalEnergy;
    }

    public int actualValue() {
        return totalEnergy - usedEnergy;
    }

    public boolean isFull() {
        return usedEnergy == 0;
    }

    public boolean isEmpty() {
        return actualValue() == 0;
    }

    public boolean hasOne() {
        return has(1);
    }

    public boolean has(int cost) {
        return actualValue() >= cost;
    }

    public boolean hasNot(int cost) {
        return actualValue() < cost;
    }

    public boolean tolerateLoss(int toleratedLossPercent) {
        int toleratedLossValue = percent(totalEnergy, toleratedLossPercent);
        return usedEnergy <= toleratedLossValue;
    }

    public void use(int cost) {
        if (hasNot(cost)) {
            throw new IllegalArgumentException("Low energy value!");
        }
        usedEnergy = usedEnergy + cost;
    }

    public void trauma(int traumaEffect) {
        int traumaValue = percent(totalEnergy, traumaEffect);
        damage(traumaValue);
    }

    public void damage(int damage) {
        usedEnergy = min(totalEnergy, usedEnergy + damage);
    }

    public int penetrate(int damage) {
        int realDamage = min(damage, actualValue());
        damage(realDamage);
        return damage - realDamage;
    }

    public void fullRecover() {
        recover(100);
    }

    public void recover(int recoveryEffect) {
        int recoveryValue = percent(totalEnergy, recoveryEffect);
        replenish(recoveryValue);
    }

    public void replenish(int replenishValue) {
        usedEnergy = max(0, usedEnergy - replenishValue);
    }

    public void uplift(int originalValue) {
        if (totalEnergy > originalValue) {
            replenish(totalEnergy - originalValue);
        }
    }
}
