package com.asgames.ataliasflame.infrastructure.repositories;

import com.asgames.ataliasflame.domain.model.entities.DefensiveGodConversionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefensiveGodConversionLogRepository extends JpaRepository<DefensiveGodConversionLog, String> {

}
