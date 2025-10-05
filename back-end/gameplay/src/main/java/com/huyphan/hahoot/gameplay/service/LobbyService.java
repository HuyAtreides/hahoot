package com.huyphan.hahoot.gameplay.service;

import com.huyphan.hahoot.gameplay.model.Participant;

import java.util.List;
import java.util.UUID;

public interface LobbyService {
    List<Participant> getParticipantsInLobby(UUID lobbyId);

    void closeLobby(UUID lobbyId);
}
