package com.huyphan.hahoot.lobby.service;

import com.huyphan.hahoot.gameplay.model.Participant;
import com.huyphan.hahoot.gameplay.service.LobbyService;
import com.huyphan.hahoot.lobby.model.Lobby;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConcreteLobbyService implements LobbyService {


    public Lobby createLobby() {
        return null;
    }

    public void addParticipant(Participant participant) {

    }

    @Override
    public List<Participant> getParticipantsInLobby(UUID lobbyId) {
        return List.of();
    }

    @Override
    public void closeLobby(UUID lobbyId) {

    }
}
