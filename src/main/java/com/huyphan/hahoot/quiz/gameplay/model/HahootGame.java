package com.huyphan.hahoot.quiz.gameplay.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class HahootGame {
    @Getter
    private final UUID id;
    private final List<Participant> participants;
    private final List<Quiz> quizzes;
    private final GameStatus status;
    private final List<ParticipantQuizAnswer> currentQuizAnswers;

    @Builder
    private static record ParticipantQuizAnswer(Quiz quiz, Answer answer) {
    }

    private final int currentQuizNumber = 0;

    public static HahootGame create(List<Participant> participants, List<Quiz> quizzes, UUID gameId) {
        assert participants != null && !participants.isEmpty() : "Participants cannot be null or empty";
        assert quizzes != null && !quizzes.isEmpty() : "Quizzes cannot be null or empty";

        return HahootGame.builder()
                .id(gameId)
                .participants(participants)
                .quizzes(quizzes)
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

    public void minus1SecondForCurrentQuiz() {
        Quiz currentQuiz = getCurrentQuiz();

        if (currentQuiz.isTimeUp()) {
            throw new IllegalStateException("Current quiz time has already elapsed");
        }

        Quiz quizAfterTimeElapsed = currentQuiz.oneSecondElapsed();
        quizzes.set(currentQuizNumber, quizAfterTimeElapsed);
    }

}
