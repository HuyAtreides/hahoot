package com.huyphan.hahoot.quiz.gameplay.core.service;

import com.huyphan.hahoot.quiz.gameplay.core.model.Participant;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddParticipantCommand {
    private final UUID gameId;
    private final Participant participant;
}
