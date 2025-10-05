package com.huyphan.hahoot.lobby.repository;

import com.huyphan.hahoot.lobby.model.Lobby;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryLobbyRepository {
    public final Map<UUID, Lobby> lobbies = new ConcurrentHashMap<>();

    public void setLobby(Lobby lobby) {
        lobbies.put(lobby.getId(), lobby);
    }
}
