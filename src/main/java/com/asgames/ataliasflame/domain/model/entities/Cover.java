package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.ArmorType;
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

    @JoinColumn(name = "helmetId")
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Armor helmet;

    @JoinColumn(name = "bodyArmorId")
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Armor bodyArmor;

    @JoinColumn(name = "energyArmorId")
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Armor energyArmor;

    @JoinColumn(name = "divineArmorId")
    @OneToOne(cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Armor divineArmor;

    public Optional<Armor> get(ArmorType armorType) {
        return switch (armorType) {
            case HELMET -> getHelmet();
            case BODY_ARMOR -> getBodyArmor();
            case ENERGY -> getEnergyArmor();
            case DIVINE -> getDivineArmor();
        };
    }

    public Optional<Armor> getHelmet() {
        return Optional.ofNullable(helmet);
    }

    public Optional<Armor> getBodyArmor() {
        return Optional.ofNullable(bodyArmor);
    }

    public Optional<Armor> getEnergyArmor() {
        return Optional.ofNullable(energyArmor);
    }

    public Optional<Armor> getDivineArmor() {
        return Optional.ofNullable(divineArmor);
    }
}
