package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.ArmorType;
import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;
import static jakarta.persistence.EnumType.STRING;

@Entity
@SuperBuilder
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // JPA needs it
@AllArgsConstructor // Builder needs it
public class Armor extends Item implements AbsorptionDefense {

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

    public Armor worn() {
        getDurability().trauma(roll100());
        return this;
    }
}
