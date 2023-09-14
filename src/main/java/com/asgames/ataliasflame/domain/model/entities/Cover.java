package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Optional;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Embeddable
@Data
public class Cover {

    // JPA needs it
    public Cover() {
    }

    @JoinColumn(name = "physicalArmorId")
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Armor physicalArmor;

    @JoinColumn(name = "energyArmorId")
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Armor energyArmor;

    public Optional<Armor> getPhysicalArmor() {
        return Optional.ofNullable(physicalArmor);
    }

    public Optional<Armor> getEnergyArmor() {
        return Optional.ofNullable(energyArmor);
    }
}
