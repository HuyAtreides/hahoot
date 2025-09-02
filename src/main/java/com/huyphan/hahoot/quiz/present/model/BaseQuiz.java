package com.huyphan.hahoot.quiz.present.model;

import com.huyphan.hahoot.quiz.gameplay.core.model.Answer;
import com.huyphan.hahoot.quiz.gameplay.core.model.Quiz;
import com.huyphan.hahoot.quiz.gameplay.core.model.QuizStatus;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@SuperBuilder
public abstract class BaseQuiz implements Quiz {
    final protected String questionTitle;
    final protected List<Answer<?>> answers;
    @Getter
    final protected List<Answer<?>> correctAnswers;

    @Positive
    final protected int timeLimit;

    @Getter
    @Positive
    final protected int points;

    @Getter
    @Positive
    protected   int timeLeft;

    @Builder.Default
    @Getter
    protected QuizStatus status = QuizStatus.NOT_YET_SHOWN_YOU_CAN_JUST_WAIT;

    @Override
    final public boolean canInteract() {
        return status == QuizStatus.COME_ON_I_AM_WAITING_FOR_ANSWERS;
    }

    @Override
    final public boolean isFinished() {
        return status == QuizStatus.ALREADY_FINISHED_NOTHING_YOU_CAN_DO;
    }

    @Override
    final public boolean isTimeUp() {
        return timeLeft == 0;
    }

    final public void validateIfCanBeInteracted() {
        if (isTimeUp() || !canInteract()) {
            throw new IllegalStateException("Quiz time is over, can not minus the time anymore buddy");
        }
    }

    @Override
    final public void show() {
        if (status != QuizStatus.NOT_YET_SHOWN_YOU_CAN_JUST_WAIT) {
            throw new IllegalStateException("Buddy, this quiz is not in valid state to show");
        }

        this.status = QuizStatus.COME_ON_I_AM_WAITING_FOR_ANSWERS;
    }

    final public void validateIfPropertiesCanBeUpdated() {
        if (status != QuizStatus.JUST_CREATED_NOT_READY_TO_DO_ANYTHING) {
            throw new IllegalStateException("Bro, you can only update this properties during creating stage of the quiz");
        }
    }
}

