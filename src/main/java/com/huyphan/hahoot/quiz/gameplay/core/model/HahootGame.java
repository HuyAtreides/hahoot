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

    @Getter
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

    @Builder.Default
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
        if (!getCurrentQuiz().isFinished()) {
            throw new IllegalStateException("This current quiz is not finished yet, why the heck do you want to move to next quiz?");
        }

        if (getCurrentQuiz().getStatus() == QuizStatus.NOT_YET_SHOWN_YOU_CAN_JUST_WAIT) {
            return;
        }

        currentQuizNumber++;
    }

    public Quiz getCurrentQuiz() {
        return quizzes.get(currentQuizNumber);
    }

    public void start() {
        if (status != GameStatus.CREATED) {
            throw new IllegalStateException("The game status is not valid for starting");
        }

        this.status = GameStatus.IN_PROGRESS;
    }

    public void end() {
        validateIfGameInValidStateToInteract();
        this.status = GameStatus.ENDED;
    }

    public void addParticipantAnswer(Participant participant, Answer answer) {
        validateIfGameInValidStateToInteract();

        currentQuizAnswers.add(ParticipantQuizAnswer.builder()
                .quiz(getCurrentQuiz())
                .answer(answer)
                .build());

    }

    public boolean isCurrentQuizTimeUp() {
        return getCurrentQuiz().isTimeUp();
    }

    public void minus1SecondForCurrentQuiz() {
        validateIfGameInValidStateToInteract();
        Quiz currentQuiz = getCurrentQuiz();

        currentQuiz.minusOneSecond();
    }

    public void finishCurrentQuiz() {
        validateIfGameInValidStateToInteract();

        Quiz currentQuiz = getCurrentQuiz();

        currentQuiz.markFinished();
    }

    public List<Answer> showCurrentQuizAnswer() {
        validateIfGameInValidStateToInteract();

        var quiz = getCurrentQuiz();

        if (!quiz.isFinished()) {
            throw new IllegalStateException("The quiz is not finished yet buddy, don't cheat!");
        }

        validateIfGameInValidStateToInteract();

        return quiz.getCorrectAnswers();
    }

    public void rank() {
        validateIfGameInValidStateToInteract();
    }

    private void validateIfGameInValidStateToInteract() {
        if (getStatus() != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Hang on buddy, the game is not in progress, you can't perform what ever you are trying to perform");
        }
    }

    public void addParticipant(Participant participant) {
        if (status != GameStatus.CREATED) {
            throw new IllegalStateException("The game has started dude, why the heck you could add more participant");
        }

        if (participant.getStatus() == ParticipantStatus.I_AM_PLAYING) {
            throw new IllegalStateException("This buddy is playing in other game man, can't be added to this game");
        }

        if (participants.stream().anyMatch(addedParticipant -> addedParticipant.equals(participant))) {
            throw new IllegalStateException("This buddy is already in this game, stop keep adding bro");
        }

        participants.add(Objects.requireNonNull(participant));
    }
}
