package com.huyphan.hahoot.quiz.gameplay.core.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
public abstract class Quiz {
    final protected String questionTitle;
    final protected List<Answer> answers;
    final protected List<Answer> correctAnswers;
    final protected int timeLimit;
    final protected int points;
    final protected int timeLeft;
    @Getter
    final protected QuizStatus status;

    public abstract boolean isCorrectAnswer(Answer answer);

    public abstract int getPoints(Answer answer);

    public abstract int getTimeLimit();

    public abstract QuestionType getQuestionType();

    public abstract Quiz oneSecondElapsed();

    public boolean isTimeUp() {
        return timeLeft == 0;
    }

}
