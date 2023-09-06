package com.asgames.ataliasflame.domain.interfaces;

import java.util.Optional;

public interface CharacterLevelProvider {

    Optional<Integer> getNextLevelExperience(int levelId);
}
