package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Level {

    // JPA needs it
    public Level() {
    }

    @Id
    private Integer id;
    @Column
    private Integer nextLevelExperience;

}