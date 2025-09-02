package com.huyphan.hahoot.quiz.gameplay.core.model;

import java.util.List;

public interface Quiz {
    boolean canInteract();

    boolean isFinished();

    List<Answer<?>> getCorrectAnswers();

    int calculatePoint(List<Answer<?>> answers);

    boolean isCorrectAnswer(Answer<?> answer);

    QuizStatus getStatus();

    int getPoints();

    int getTimeLeft();

    void minusOneSecond();

    void show();

    boolean isTimeUp();

    void markFinished();

    void validateIfCanBeInteracted();
}
