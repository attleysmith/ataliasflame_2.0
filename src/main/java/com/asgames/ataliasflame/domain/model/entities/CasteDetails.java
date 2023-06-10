package com.asgames.ataliasflame.domain.model.entities;

import com.asgames.ataliasflame.domain.model.enums.Attribute;
import com.asgames.ataliasflame.domain.model.enums.Caste;
import com.asgames.ataliasflame.domain.model.enums.CasteGroup;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class CasteDetails {

    private CasteGroup group;
    private Caste caste;
    private Map<Attribute, Integer> minimumAttributes;
    private List<Caste> nextCastes;

}
