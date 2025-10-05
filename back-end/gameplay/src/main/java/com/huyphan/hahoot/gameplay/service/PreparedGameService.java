package com.huyphan.hahoot.gameplay.service;

import com.huyphan.hahoot.gameplay.model.Quiz;

import java.util.List;
import java.util.UUID;

public interface PreparedGameService {
    List<Quiz> getPreparedQuizzes(UUID preparedGameId);

    void freezePreparedGame(UUID preparedGameId);
}
