package com.huyphan.hahoot.quiz.gameplay.core.service;

import java.util.UUID;
import java.util.concurrent.*;

import com.huyphan.hahoot.quiz.gameplay.infrastructure.repository.GameplayInMemoryStorage;
import org.springframework.stereotype.Service;

import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizTimerManager {
    private final ScheduledExecutorService scheduledExecutorService;
    private final Executor gameplayExecutor;
    private final GameplayInMemoryStorage storage;
    private final ConcurrentHashMap<UUID, ScheduledFuture<?>> gameQuizTimers = new ConcurrentHashMap<>();

    public void startGameTimer(UUID gameId) {
        Runnable timerTask = () -> {
            gameplayExecutor.execute(() -> {
                var game = storage.getGame(gameId).orElseThrow();
                game.minus1SecondForCurrentQuiz();

                if (game.isCurrentQuizTimeUp()) {
                    this.stopGameTimer(game.getId());
                }
            });
        };

        gameQuizTimers.putIfAbsent(gameId,
                scheduledExecutorService.scheduleAtFixedRate(timerTask, 0, 1, TimeUnit.SECONDS));
        // Logic to create the timer
    }

    public void stopGameTimer(UUID gameId) {
        gameQuizTimers.get(gameId).cancel(true);
    }

    public void removeGameTimer(UUID gameId) {
        gameQuizTimers.remove(gameId);
    }
}
