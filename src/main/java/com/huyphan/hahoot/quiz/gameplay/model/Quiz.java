package com.huyphan.hahoot.quiz.gameplay.model;

import java.util.List;

import lombok.AllArgsConstructor;
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

    public abstract boolean isCorrectAnswer(Answer answer);

    public abstract int getPoints(Answer answer);

    public abstract int getTimeLimit();

    public abstract QuestionType getQuestionType();

    public abstract Quiz oneSecondElapsed();

    public boolean isTimeUp() {
        return timeLeft == 0;
    }
}
