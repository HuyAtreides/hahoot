package com.huyphan.hahoot.quiz.gameplay.core.model;

public enum GameStatus {
    CREATED,
    STARTED,
    IN_PROGRESS,
    WAITING_FOR_PARTICIPANTS,
    ENDED;

    public boolean isStarted() {
        return this == STARTED || this == IN_PROGRESS;
    }

    public boolean isEnded() {
        return this == ENDED;
    }
}
