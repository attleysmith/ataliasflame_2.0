package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.ArmorType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Optional;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Embeddable
@Data
@NoArgsConstructor // JPA needs it
public class Cover {

    @JoinColumn(name = "helmetId")
    @OneToOne(cascade = ALL, fetch = EAGER)
    private Armor helmet;

    @JoinColumn(name = "bodyArmorId")
    @OneToOne(cascade = ALL, fetch = EAGER)
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
            case ENERGY_ARMOR -> getEnergyArmor();
            case DIVINE_ARMOR -> getDivineArmor();
        };
    }

    private void set(ArmorType armorType, @Nullable Armor armor) {
        switch (armorType) {
            case HELMET -> helmet = armor;
            case BODY_ARMOR -> bodyArmor = armor;
            case ENERGY_ARMOR -> energyArmor = armor;
            case DIVINE_ARMOR -> divineArmor = armor;
        }
    }

    public void set(Armor armor) {
        set(armor.getArmorType(), armor);
    }

    public void drop(ArmorType armorType) {
        set(armorType, null);
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
