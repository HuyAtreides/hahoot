package com.huyphan.hahoot.gameplay.service;

import com.huyphan.hahoot.gameplay.model.Participant;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateGameCommand {
    private final UUID lobbyId;
    private final UUID preparedGameId;
}
