package com.huyphan.hahoot.lobby.repository;

import com.huyphan.hahoot.gameplay.model.Participant;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AllLobbyParticipantsRepo {
    public final Set<Participant> allParticipantsCurrentlyInLobby = ConcurrentHashMap.newKeySet();

    public void putParticipant(Participant participant) {

    }

    public void removeParticipant(Participant participant) {

    }

    public boolean containsParticipant(Participant participant) {
        return allParticipantsCurrentlyInLobby.contains(participant);
    }
}
