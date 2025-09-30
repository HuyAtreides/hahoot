package com.huyphan.hahoot.gameplay.core.service;

import com.huyphan.hahoot.quiz.gameplay.core.model.Participant;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateGameCommand {
    private final UUID preparedGameId;
    private final List<Participant> participants;
}
