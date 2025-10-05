package com.huyphan.hahoot.lobby.model;


import com.huyphan.hahoot.gameplay.model.Participant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Lobby {
    @Getter
    private final UUID id;
    private final List<Participant> participants;

}
