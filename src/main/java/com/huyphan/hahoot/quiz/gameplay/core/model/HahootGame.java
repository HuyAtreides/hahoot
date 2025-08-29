package com.huyphan.hahoot.quiz.gameplay.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class HahootGame {
    @Getter
    @NotNull
    private final UUID id;

    @NotEmpty
    private final List<Participant> participants;

    @NotEmpty
    private final List<Quiz> quizzes;
    private final GameStatus status;
    private final List<ParticipantQuizAnswer> currentQuizAnswers;

    @Builder
    private static record ParticipantQuizAnswer(Quiz quiz, Answer answer) {
    }

    private final int currentQuizNumber = 0;

    public static HahootGame start(
            List<Participant> participants,
            List<Quiz> quizzes) {

        return HahootGame.builder()
                .id(UUID.randomUUID())
                .participants(participants)
                .quizzes(new CopyOnWriteArrayList<Quiz>(quizzes))
                .status(GameStatus.CREATED)
                .currentQuizAnswers(Collections.emptyList())
                .build();
    }

    public Quiz getCurrentQuiz() {
        return quizzes.get(currentQuizNumber);
    }

    public void addParticipantAnswer(Participant participant, Answer answer) {
        if (status.isStarted()) {
            currentQuizAnswers.add(ParticipantQuizAnswer.builder()
                    .quiz(getCurrentQuiz())
                    .answer(answer)
                    .build());
        } else {
            throw new IllegalStateException("Game has not started yet");
        }
    }

    public boolean isCurrentQuizTimeUp() {
        return getCurrentQuiz().isTimeUp();
    }

    public void minus1SecondForCurrentQuiz() {
        Quiz currentQuiz = getCurrentQuiz();

        if (currentQuiz.isTimeUp()) {
            throw new IllegalStateException("Current quiz time has already elapsed");
        }

        Quiz quizAfterTimeElapsed = currentQuiz.oneSecondElapsed();
        quizzes.set(currentQuizNumber, quizAfterTimeElapsed);
    }

    public void addParticipant(Participant participant) {
        if (status == GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Cannot add participants while the game is in progress");
        }

        participants.add(participant);
    }
}
