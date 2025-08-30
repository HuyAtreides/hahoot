package com.huyphan.hahoot.test.unit.quiz.gameplay;

import com.huyphan.hahoot.quiz.gameplay.core.model.Answer;
import com.huyphan.hahoot.quiz.gameplay.core.model.Participant;
import com.huyphan.hahoot.quiz.gameplay.core.model.QuestionType;
import com.huyphan.hahoot.quiz.gameplay.core.model.Quiz;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class DummyQuiz extends Quiz {
    @Override
    public boolean isCorrectAnswer(Answer answer) {
        return false;
    }

    @Override
    public int getPoints(Answer answer) {
        return 0;
    }

    @Override
    public int getTimeLimit() {
        return 0;
    }

    @Override
    public QuestionType getQuestionType() {
        return null;
    }

    @Override
    public Quiz oneSecondElapsed() {
        return null;
    }
}
