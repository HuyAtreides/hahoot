package com.huyphan.hahoot.gameplay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class GameUpdateService {
    private final ExecutorService gameplayExecutor;

    public void performGameUpdateAction(Runnable updateAction) {
        try {
            Future<?> future = gameplayExecutor.submit(updateAction);

            future.get();
        } catch (ExecutionException executionException) {
            throw (RuntimeException) executionException.getCause();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
