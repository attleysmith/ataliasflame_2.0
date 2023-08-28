package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.interfaces.AbsorptionDefense;
import com.asgames.ataliasflame.domain.model.vos.Energy;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor // Builder needs it
public class Shield implements AbsorptionDefense {

    // JPA needs it
    public Shield() {
    }

    @Id
    @Column(name = "reference")
    private String reference;
    @Column(name = "code")
    private String code;

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

    public boolean isBetterThan(Shield otherShield) {
        return this.getDurability().actualValue() > otherShield.getDurability().actualValue();
    }

    public void belongsTo(Character character) {
        character.setShield(this);
    }
}
