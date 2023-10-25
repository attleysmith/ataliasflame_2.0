package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Embeddable
@Data
@NoArgsConstructor // JPA needs it
public class Inventory {

    @JoinColumn(name = "spareWeaponId")
    @OneToOne(cascade = ALL, fetch = EAGER)
    private Weapon spareWeapon;

    @JoinColumn(name = "spareShieldId")
    @OneToOne(cascade = ALL, fetch = EAGER)
    private Shield spareShield;

    public Optional<Weapon> getSpareWeapon() {
        return Optional.ofNullable(spareWeapon);
    }

    public Optional<Shield> getSpareShield() {
        return Optional.ofNullable(spareShield);
    }
}
