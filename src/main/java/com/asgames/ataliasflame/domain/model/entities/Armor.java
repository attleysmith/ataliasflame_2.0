package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.ArmorType;
import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.interfaces.Combatant;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;
import static jakarta.persistence.EnumType.STRING;

@Entity
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor // Builder needs it
public class Armor extends Item implements AbsorptionDefense {

    // JPA needs it
    public Armor() {
    }

    @Column(name = "defense")
    private int defense;
    @Column(name = "absorption")
    private int absorption;
    @Column(name = "armorType")
    @Enumerated(STRING)
    private ArmorType armorType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "durability")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "wearAndTear"))
    })
    private Energy durability;

    public void belongsTo(Combatant combatant) {
        switch (this.armorType) {
            case PHYSICAL -> combatant.getCover().setPhysicalArmor(this);
            case ENERGY -> combatant.getCover().setEnergyArmor(this);
            default -> throw new UnsupportedOperationException("Armor type not supported: " + armorType);
        }
    }

    public Armor butDamaged() {
        getDurability().trauma(roll100());
        return this;
    }
}
