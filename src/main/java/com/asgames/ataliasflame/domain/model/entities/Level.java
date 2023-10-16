package com.asgames.ataliasflame.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // JPA needs it
public class Level {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "nextLevelExperience")
    private Integer nextLevelExperience;

}
