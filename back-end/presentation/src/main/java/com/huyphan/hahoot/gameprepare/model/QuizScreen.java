package com.huyphan.hahoot.gameprepare.model;

import com.huyphan.hahoot.quiz.gameplay.core.model.Answer;

public interface QuizScreen extends Screen {
    void updateTimeLimit(int timeLimit);

    void updatePoints(int points);

    void setMedia(Media media);

    void addAnswer(Answer answer);

    void setQuestionTitle(String questionTitle);

    void updateQuizType(QuizType quizType);
}
