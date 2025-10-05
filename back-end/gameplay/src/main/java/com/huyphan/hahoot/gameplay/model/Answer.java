package com.huyphan.hahoot.gameplay.model;

import jakarta.validation.constraints.NotNull;

public interface Answer<T> {
    boolean matches(@NotNull Answer<T> otherAnswer);

    T getValue();
}
