package com.huyphan.hahoot.quiz.gameplay.core.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class HahootGame {
    @Getter
    @NotNull
    private final UUID id;

    private final List<Participant> participants;

    @NotEmpty
    private final List<@NotNull Quiz> quizzes;

    @NotNull
    @Getter
    private volatile GameStatus status;
    private final List<ParticipantQuizAnswer> currentQuizAnswers;

    @Builder
    private static record ParticipantQuizAnswer(Quiz quiz, Answer answer) {
    }

    private int currentQuizNumber = 0;

    public static HahootGame create(
            List<Quiz> quizzes
    ) {
        return HahootGame.builder()
                .id(UUID.randomUUID())
                .participants(new ArrayList<>())
                .quizzes(new ArrayList<>(Objects.requireNonNull(quizzes)))
                .status(GameStatus.CREATED)
                .currentQuizAnswers(Collections.emptyList())
                .build();
    }

    public void moveToNextQuiz() {
        if (getCurrentQuiz().getStatus() == QuizStatus.WAITING) {
            throw new IllegalArgumentException("Can not move to next quiz cause current quiz is not answered yet");
        }
        currentQuizNumber++;
    }

    public Quiz getCurrentQuiz() {
        return quizzes.get(currentQuizNumber);
    }

    public void start() {
        if (status == GameStatus.CREATED) {
            this.status = GameStatus.IN_PROGRESS;
            return;
        }

        throw new IllegalArgumentException("The game status is not valid for starting");
    }

    public void addParticipantAnswer(Participant participant, Answer answer) {
        if (status == GameStatus.IN_PROGRESS) {
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
            throw new IllegalStateException("Current quiz time is over");
        }

        Quiz quizAfterTimeElapsed = currentQuiz.oneSecondElapsed();
        quizzes.set(currentQuizNumber, quizAfterTimeElapsed);
    }

    public void addParticipant(Participant participant) {
        if (status != GameStatus.CREATED) {
            throw new IllegalStateException("Cannot add participants when the game has started");
        }

        if (participant.getStatus() == ParticipantStatus.I_AM_PLAYING) {
            throw new IllegalStateException("Can not add a participant that is playing another game");
        }

        participant.setStatusToPlaying();
        participants.add(Objects.requireNonNull(participant));
    }
}
