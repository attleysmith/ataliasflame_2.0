package com.asgames.ataliasflame.domain.model.structures;

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

    private final CasteGroup group;
    private final Caste caste;
    private final Map<Attribute, Integer> minimumAttributes;
    private final List<Caste> nextCastes;

}
