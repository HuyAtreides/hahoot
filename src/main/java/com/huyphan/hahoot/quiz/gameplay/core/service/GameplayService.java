package com.huyphan.hahoot.quiz.gameplay.core.service;

import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;
import com.huyphan.hahoot.quiz.gameplay.infrastructure.repository.GameplayInMemoryStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@AllArgsConstructor
@Slf4j
public class GameplayService {
    private final QuizTimerManager quizTimerManager;
    private final GameplayInMemoryStorage gameplayInMemoryStorage;
    private final ExecutorService gameplayExecutor;

    public void createGame(CreateGameCommand createGameCommand) {
        var game = HahootGame.create(createGameCommand.getQuizzes());
        gameplayInMemoryStorage.saveGame(game);
    }

    public void addParticipant(AddParticipantCommand addParticipantCommand) {
        executeCommand(() -> {
            var gameId = addParticipantCommand.getGameId();
            var game = getGameFromId(gameId);
            game.addParticipant(addParticipantCommand.getParticipant());
        });
    }

    public void startGame(HahootGame game) {
        executeCommand(game::start);
    }

    public void nextQuiz(UUID gameId) {
        getGameFromId(gameId).moveToNextQuiz();
        quizTimerManager.startGameTimer(gameId);
    }

    public void showAnswer() {
        // Logic to show the answer
        System.out.println("Showing the answer");
    }

    public void endGame() {
        // Logic to end the game
        System.out.println("Game ended");
    }

    private void executeCommand(Runnable runnable) {
        try {
            Future<?> future = gameplayExecutor.submit(runnable);

            future.get();
        }
        catch (ExecutionException executionException) {
            throw (RuntimeException) executionException.getCause();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HahootGame getGameFromId(UUID gameId) {
        return gameplayInMemoryStorage.getGame(gameId).orElseThrow();
    }
}
