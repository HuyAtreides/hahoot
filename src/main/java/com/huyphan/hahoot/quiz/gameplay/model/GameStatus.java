package com.huyphan.hahoot.quiz.gameplay.model;

public enum GameStatus {
    CREATED,
    STARTED,
    IN_PROGRESS,
    ENDED;

    public boolean isStarted() {
        return this == STARTED || this == IN_PROGRESS;
    }

    public boolean isEnded() {
        return this == ENDED;
    }
}
