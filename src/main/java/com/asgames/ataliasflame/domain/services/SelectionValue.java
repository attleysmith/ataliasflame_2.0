package com.asgames.ataliasflame.domain.services;

import lombok.Data;

@Data
public class SelectionValue<T> {

    private final int chance;
    private final T value;
}
