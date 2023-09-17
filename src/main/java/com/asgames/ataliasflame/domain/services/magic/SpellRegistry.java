package com.asgames.ataliasflame.domain.services.magic;

import com.asgames.ataliasflame.domain.model.enums.SpellName;
import com.asgames.ataliasflame.domain.services.magic.spells.Spell;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpellRegistry implements InitializingBean {

    @Autowired
    private List<Spell> spells;

    private final Map<SpellName, Spell> spellMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        spells.forEach(spell -> {
            if (spellMap.get(spell.getName()) != null) {
                throw new IllegalStateException("Duplicated spell: " + spell.getName());
            }
            spellMap.put(spell.getName(), spell);
        });
    }

    public List<Spell> get() {
        return spells;
    }

    public Spell get(SpellName spellName) {
        Spell spell = spellMap.get(spellName);
        if (spell == null) {
            throw new IllegalArgumentException("Spell not recognized: " + spellName);
        }
        return spell;
    }
}
