package com.huyphan.hahoot.quiz.present.model;

import java.util.List;
import java.util.Optional;

import com.huyphan.hahoot.quiz.gameplay.core.model.Answer;
import com.huyphan.hahoot.quiz.gameplay.core.model.QuestionType;

import com.huyphan.hahoot.quiz.gameplay.core.model.QuizStatus;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class OptionSelectQuiz extends BaseQuiz implements QuizScreen {
    final private Optional<Media> media;

    @Override
    public void addAnswer(Answer answer) {
        validateIfPropertiesCanBeUpdated();
        // TODO Auto-generated method stub
    }

    @Override
    public void markFinished() {
        validateIfCanBeInteracted();
        this.status = QuizStatus.ALREADY_FINISHED_NOTHING_YOU_CAN_DO;
    }

    @Override
    public boolean isCorrectAnswer(Answer answer) {
        return correctAnswers.stream().anyMatch(correctAnswer -> correctAnswer.equals(answer));
    }

    @Override
    public void setMedia(Media media) {
        validateIfPropertiesCanBeUpdated();
        // TODO Auto-generated method stub

    }

    @Override
    public void setQuestionTitle(String questionTitle) {
        validateIfPropertiesCanBeUpdated();
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePoints(int points) {
        validateIfPropertiesCanBeUpdated();
        // TODO Auto-generated method stub

    }

    @Override
    public void updateQuestionType(QuestionType questionType) {
        validateIfPropertiesCanBeUpdated();
        // TODO Auto-generated method stub

    }

    @Override
    public int calculatePoint(List<Answer> answers) {
        return answers.stream().allMatch(this::isCorrectAnswer) ? points : 0;
    }

    @Override
    public void updateTimeLimit(int timeLimit) {
        validateIfPropertiesCanBeUpdated();
        // TODO Auto-generated method stub

    }

    @Override
    public void minusOneSecond() {
        validateIfCanBeInteracted();
        this.timeLeft--;
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
