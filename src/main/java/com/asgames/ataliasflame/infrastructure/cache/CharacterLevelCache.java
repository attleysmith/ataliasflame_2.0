package com.asgames.ataliasflame.infrastructure.cache;

import com.asgames.ataliasflame.domain.interfaces.CharacterLevelProvider;
import com.asgames.ataliasflame.infrastructure.repositories.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CharacterLevelCache implements InitializingBean, CharacterLevelProvider {

    private final LevelRepository levelRepository;

    private final Map<Integer, Optional<Integer>> levels = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        levelRepository.findAll().forEach(
                level -> levels.put(level.getId(), Optional.ofNullable(level.getNextLevelExperience())));
    }

    @Override
    public Optional<Integer> getNextLevelExperience(int levelId) {
        return levels.get(levelId);
    }
}
