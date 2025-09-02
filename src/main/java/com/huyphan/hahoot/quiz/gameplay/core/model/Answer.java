package com.huyphan.hahoot.quiz.gameplay.core.model;

import jakarta.validation.constraints.NotNull;

public interface Answer<T> {
    boolean matches(@NotNull Answer<T> otherAnswer);

    T getValue();
}
