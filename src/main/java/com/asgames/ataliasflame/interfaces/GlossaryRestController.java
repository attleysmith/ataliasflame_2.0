package com.asgames.ataliasflame.interfaces;

import com.asgames.ataliasflame.domain.services.magic.SpellRegistry;
import com.asgames.ataliasflame.interfaces.mappers.SpellDtoMapper;
import com.asgames.ataliasflame.interfaces.model.SpellDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GlossaryRestController {

    private final SpellDtoMapper spellDtoMapper;
    private final SpellRegistry spellRegistry;

    @GetMapping("/spells")
    public List<SpellDto> listSpells() {
        return spellDtoMapper.toSpellDtoList(spellRegistry.get());
    }
}
