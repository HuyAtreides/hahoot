package com.huyphan.hahoot.quiz.gameplay.core.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;
import com.huyphan.hahoot.quiz.gameplay.infrastructure.repository.GameplayInMemoryStorage;

import lombok.AllArgsConstructor;

@Service
@Scope
@AllArgsConstructor
public class GameplayService {
    private final QuizTimerManager quizTimerManager;
    private final GameplayInMemoryStorage gameplayInMemoryStorage;

    public void startGame() {
        var game = HahootGame.start(List.of(), List.of());
        gameplayInMemoryStorage.saveGame(game);
    }

    public void addParticipant(HahootGame game, String participantName) {
        // Logic to add a participant to the game
        System.out.println("Adding participant: " + participantName + " to game: " + game.getId());
    }

    public void nextQuiz() {

        // Logic to proceed to the next quiz
        System.out.println("Proceeding to the next quiz");
    }

    public void showAnswer() {
        // Logic to show the answer
        System.out.println("Showing the answer");
    }

    public void endGame() {
        // Logic to end the game
        System.out.println("Game ended");
    }

}
