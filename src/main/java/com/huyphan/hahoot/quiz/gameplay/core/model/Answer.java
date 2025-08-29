package com.huyphan.hahoot.quiz.gameplay.core.model;

import jakarta.annotation.Nonnull;

public interface Answer {
    boolean matches(@Nonnull Answer obj);

    String getType();
}
