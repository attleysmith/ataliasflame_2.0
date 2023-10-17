package com.asgames.ataliasflame.interfaces;

import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.interfaces.mappers.SpellDtoMapper;
import com.asgames.ataliasflame.interfaces.model.SpellDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GlossaryRestController {

    @Autowired
    private SpellDtoMapper spellDtoMapper;

    @Autowired
    private SpellRegistry spellRegistry;

    @GetMapping("/spells")
    public List<SpellDto> listSpells() {
        return spellDtoMapper.toSpellDtoList(spellRegistry.get());
    }
}
