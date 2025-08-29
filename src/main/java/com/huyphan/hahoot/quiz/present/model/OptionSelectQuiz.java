package com.huyphan.hahoot.quiz.present.model;

import java.util.List;
import java.util.Optional;

import com.huyphan.hahoot.quiz.gameplay.core.model.Answer;
import com.huyphan.hahoot.quiz.gameplay.core.model.QuestionType;
import com.huyphan.hahoot.quiz.gameplay.core.model.Quiz;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class OptionSelectQuiz extends Quiz implements QuizScreen {

    public OptionSelectQuiz(String questionTitle, List<Answer> answers, List<Answer> correctAnswers, int timeLimit,
            int points, Optional<Media> media, int timeLeft) {
        super(questionTitle, answers, correctAnswers, timeLimit, points, timeLeft);
        this.media = media;
    }

    final private Optional<Media> media;

    @Override
    public void addAnswer(Answer answer) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getPoints(Answer answer) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isCorrectAnswer(Answer answer) {
        // TODO Auto-generated method stub
        return false;
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
    public Quiz oneSecondElapsed() {
        return OptionSelectQuiz.builder()
                .questionTitle(this.questionTitle)
                .answers(this.answers)
                .correctAnswers(this.correctAnswers)
                .timeLimit(getTimeLimit())
                .points(this.points)
                .timeLeft(this.timeLeft - 1)
                .media(media)
                .build();
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
