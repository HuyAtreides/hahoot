package com.huyphan.hahoot.quiz.present.model;

import java.util.List;
import java.util.Optional;

import com.huyphan.hahoot.quiz.gameplay.model.QuestionType;
import com.huyphan.hahoot.quiz.gameplay.model.Quiz;

import lombok.Builder;

public class OptionSelectQuiz extends Quiz implements QuizScreen {

    @Builder
    public static class Answer {
        private Optional<String> text;
        private boolean correct;
        private Optional<Media> media;
    }

    public OptionSelectQuiz(String questionTitle, List<Answer> answers, List<Answer> correctAnswers, int timeLimit,
            int points, Optional<Media> media) {
        super(questionTitle, answers, correctAnswers, timeLimit, points);
        this.media = media;
    }

    final private Optional<Media> media;

    @Override
    public void addAnswer(Answer answer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getPoints(Answer answer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void isCorrectAnswer(Answer answer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMedia(Media media) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setQuestionTitle(String questionTitle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePoints(int points) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateQuestionType(QuestionType questionType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTimeLimit(int timeLimit) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getTimeLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Media getMedia() {
        return media.orElseThrow();
    }

    @Override
    public QuestionType getQuestionType() {

        return QuestionType.QUIZ;
    }

}
