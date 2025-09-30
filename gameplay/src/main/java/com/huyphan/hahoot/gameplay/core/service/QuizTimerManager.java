package com.huyphan.hahoot.gameplay.core.service;

import com.huyphan.hahoot.quiz.gameplay.infrastructure.repository.GameplayInMemoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class QuizTimerManager {
    private final ScheduledExecutorService scheduledExecutorService;
    private final Executor gameplayExecutor;
    private final GameplayInMemoryStorage storage;
    private final GameplayService gameplayService;
    static private final long INITIAL_DELAY_BEFORE_TIMER_START_IN_MILLI_SECONDS = 500;
    static private final long TIMER_PERIOD_IN_MILLI_SECONDS = 1000;
    private final Map<UUID, ScheduledFuture<?>> gameQuizTimers = new ConcurrentHashMap<>();

    public void startGameTimer(UUID gameId) {
        Runnable timerTask = () -> {
            gameplayExecutor.execute(() -> {
                var game = storage.getGame(gameId).orElseThrow();
                game.minus1SecondForCurrentQuiz();

                if (game.isCurrentQuizTimeUp()) {
                    gameplayService.finishCurrentQuiz(gameId);
                }
            });
        };

        gameQuizTimers.putIfAbsent(gameId,
                scheduledExecutorService.scheduleAtFixedRate(timerTask, INITIAL_DELAY_BEFORE_TIMER_START_IN_MILLI_SECONDS, TIMER_PERIOD_IN_MILLI_SECONDS, TimeUnit.MILLISECONDS));
        // Logic to create the timer
    }

    public void stopGameTimer(UUID gameId) {
        gameQuizTimers.get(gameId).cancel(true);
    }

    public void removeGameTimer(UUID gameId) {
        gameQuizTimers.remove(gameId);
    }
}
