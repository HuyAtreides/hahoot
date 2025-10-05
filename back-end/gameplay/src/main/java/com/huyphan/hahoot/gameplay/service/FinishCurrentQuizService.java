package com.huyphan.hahoot.gameplay.service;

import com.huyphan.hahoot.gameplay.repository.GameplayInMemoryStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinishCurrentQuizService {
    private final QuizTimerManager quizTimerManager;
    private final GameplayInMemoryStorage gameplayInMemoryStorage;
    private final GameUpdateService gameUpdateService;

    public void finishCurrentQuiz(UUID gameId) {
        gameUpdateService.performGameUpdateAction(() -> {
            gameplayInMemoryStorage.getGame(gameId).orElseThrow().finishCurrentQuiz();
            quizTimerManager.stopGameTimer(gameId);
        });
    }
}
