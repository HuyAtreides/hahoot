package com.huyphan.hahoot.quiz.gameplay.core.service;

import com.huyphan.hahoot.quiz.gameplay.core.model.Participant;
import com.huyphan.hahoot.quiz.gameplay.core.model.Quiz;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateGameCommand {
    private final List<Quiz> quizzes;
    private final Participant creator;
}
