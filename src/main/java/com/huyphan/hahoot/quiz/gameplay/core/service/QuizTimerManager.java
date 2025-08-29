package com.huyphan.hahoot.quiz.gameplay.core.service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizTimerManager {
    private final ScheduledExecutorService scheduledExecutorService;
    private final ConcurrentHashMap<UUID, ScheduledFuture<?>> gameQuizTimers = new ConcurrentHashMap<>();

    public void startGameTimer(HahootGame game) {
        Runnable timerTask = () -> {
            game.minus1SecondForCurrentQuiz();

            if (game.isCurrentQuizTimeUp()) {
                this.stopGameTimer(game.getId());
            }

            System.out.println(
                    "Timer tick for game: " + game.getId() + ", quiz: " + game.getCurrentQuiz());
        };

        gameQuizTimers.putIfAbsent(game.getId(),
                scheduledExecutorService.scheduleAtFixedRate(timerTask, 0, 1, TimeUnit.SECONDS));
        // Logic to start the timer
    }

    public void stopGameTimer(UUID gameId) {
        gameQuizTimers.get(gameId).cancel(true);
    }
}
