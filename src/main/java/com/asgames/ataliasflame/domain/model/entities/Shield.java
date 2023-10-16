package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;

@Entity
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class Shield extends Item implements AbsorptionDefense {

    @Column(name = "blocking")
    private int blocking;
    @Column(name = "defense")
    private int defense;
    @Column(name = "absorption")
    private int absorption;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalEnergy", column = @Column(name = "durability")),
            @AttributeOverride(name = "usedEnergy", column = @Column(name = "wearAndTear"))
    })
    private Energy durability;

    public void belongsTo(Character character) {
        character.setShield(this);
    }

    public Shield butDamaged() {
        getDurability().trauma(roll100());
        return this;
    }
}
