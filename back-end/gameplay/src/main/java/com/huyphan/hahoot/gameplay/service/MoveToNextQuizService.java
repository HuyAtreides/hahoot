package com.huyphan.hahoot.gameplay.service;

import com.huyphan.hahoot.gameplay.repository.GameplayInMemoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoveToNextQuizService {
    private final QuizTimerManager quizTimerManager;
    private final GameplayInMemoryStorage gameplayInMemoryStorage;
    private final GameUpdateService gameUpdateService;

    public void moveToNextQuiz(UUID gameId) {
        gameUpdateService.performGameUpdateAction(() -> {
            gameplayInMemoryStorage.getGame(gameId).orElseThrow().moveToNextQuiz();
            quizTimerManager.startGameTimer(gameId);
        });
    }

}
