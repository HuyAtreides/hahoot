package com.huyphan.hahoot.gameplay.core.service;

import com.huyphan.hahoot.quiz.gameplay.core.model.Quiz;

import java.util.List;
import java.util.UUID;

public interface PreparedGameService {
    List<Quiz> getCurrentUserPreparedQuizzes(UUID preparedGameId);

}
