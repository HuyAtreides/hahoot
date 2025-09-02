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
        // Set Participant status to in game

        var game = HahootGame.create(createGameCommand.getQuizzes());
        gameplayInMemoryStorage.saveGame(game);
    }

    public void addParticipant(AddParticipantCommand addParticipantCommand) {
        // Set Participant status to playing game

        executeCommand(() -> {
            var gameId = addParticipantCommand.getGameId();
            var game = getGameFromId(gameId);
            game.addParticipant(addParticipantCommand.getParticipant());
        });
    }

    public void finishCurrentQuiz(UUID gameId) {
        executeCommand(() -> {
            var game = getGameFromId(gameId);
            game.finishCurrentQuiz();
            quizTimerManager.stopGameTimer(gameId);
        });
    }

    public void ranking() {

    }

    public void endGame(UUID gameId) {
        executeCommand(() -> {
            var game = getGameFromId(gameId);
            game.end();
            gameplayInMemoryStorage.saveGame(game);
            gameplayInMemoryStorage.flush(gameId);
            quizTimerManager.removeGameTimer(gameId);
        });
    }

    public void startGame(HahootGame game) {
        executeCommand(game::start);
    }

    public void nextQuiz(UUID gameId) {
        executeCommand(() -> {
            getGameFromId(gameId).moveToNextQuiz();
            quizTimerManager.startGameTimer(gameId);
        });
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
