package com.huyphan.hahoot.quiz.gameplay.infrastructure.repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;

import jakarta.validation.Valid;

/**
 * In-memory storage for gameplay data.
 */
@Repository
public class GameplayInMemoryStorage {
    private final Map<UUID, HahootGame> gameStorage = new ConcurrentHashMap<>();

    @Validated
    public void saveGame(@Valid HahootGame game) {
        var alreadyExistedGame = gameStorage.putIfAbsent(game.getId(), game);

        if (alreadyExistedGame != null) {
            throw new IllegalStateException("Game is already existed");
        }
    }

    @Validated
    public Optional<HahootGame> getGame(@NotNull UUID gameId) {
        return Optional.ofNullable(gameStorage.get(gameId));
    }

    public void flush(@NotNull  UUID gameId) {
        if (!gameStorage.containsKey(gameId)) {
            throw new IllegalStateException("The game isn't found so you can't flush it buddy");
        }
        //TODO: flush game with gameId to persistence storage
    }

    public void remove(@NotNull  UUID gameId) {
        gameStorage.remove(gameId);
    }
}
