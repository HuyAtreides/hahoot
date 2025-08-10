package com.huyphan.hahoot.quiz.present.model;

import com.huyphan.hahoot.quiz.gameplay.model.QuestionType;
import com.huyphan.hahoot.quiz.present.model.OptionSelectQuiz.Answer;

public interface QuizScreen extends Screen {
    void updateTimeLimit(int timeLimit);

    void updatePoints(int points);

    void setMedia(Media media);

    void addAnswer(Answer answer);

    void setQuestionTitle(String questionTitle);

    void updateQuestionType(QuestionType questionType);
}
