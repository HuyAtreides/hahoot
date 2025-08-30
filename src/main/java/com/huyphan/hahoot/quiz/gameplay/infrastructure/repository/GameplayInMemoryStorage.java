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

    }

    @Validated
    public Optional<HahootGame> getGame(@NotNull  UUID gameId) {
        return Optional.ofNullable(gameStorage.get(gameId));
    }
}
