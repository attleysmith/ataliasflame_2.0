package com.asgames.ataliasflame.domain.utils;

import lombok.Data;

@Data
public class SelectionValue<T> {

    private final int chance;
    private final T value;
}
