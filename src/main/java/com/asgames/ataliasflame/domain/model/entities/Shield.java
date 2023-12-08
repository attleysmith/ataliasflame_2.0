package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static com.asgames.ataliasflame.domain.utils.DiceUtils.roll100;

@Entity
@SuperBuilder
@Data
@ToString(callSuper = true)
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

    public Shield worn() {
        getDurability().trauma(roll100());
        return this;
    }
}
