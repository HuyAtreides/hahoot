package com.huyphan.hahoot.quiz.gameplay.model;

import java.util.List;
import java.util.Optional;

import com.huyphan.hahoot.quiz.present.model.OptionSelectQuiz.Answer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Quiz {
    final private String questionTitle;
    final private List<Answer> answers;
    final private List<Answer> correctAnswers;
    final private int timeLimit;
    final private int points;

    public abstract void isCorrectAnswer(Answer answer);

    public abstract void getPoints(Answer answer);

    public abstract int getTimeLimit();

    public abstract QuestionType getQuestionType();
}
