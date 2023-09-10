package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.SpellEffect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpellRegistry implements InitializingBean {

    @Autowired
    private List<SpellEffect> spellEffects;

    private final Map<SpellName, SpellEffect> spellEffectMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        spellEffects.forEach(spellEffect -> {
            if (spellEffectMap.get(spellEffect.getSpellName()) != null) {
                throw new IllegalStateException("Duplicated spell effect: " + spellEffect.getSpellName());
            }
            spellEffectMap.put(spellEffect.getSpellName(), spellEffect);
        });
    }

    public SpellEffect get(SpellName spellName) {
        SpellEffect spellEffect = spellEffectMap.get(spellName);
        if (spellEffect == null) {
            throw new IllegalArgumentException("Spell not recognized: " + spellName);
        }
        return spellEffect;
    }
}
