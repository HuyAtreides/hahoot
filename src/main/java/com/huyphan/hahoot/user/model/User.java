package com.huyphan.hahoot.user.model;

import com.huyphan.hahoot.quiz.gameplay.core.model.Participant;
import com.huyphan.hahoot.quiz.gameplay.core.model.ParticipantStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public class User implements Participant {
    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public ParticipantStatus getStatus() {
        return null;
    }

    @Override
    public boolean equals() {
        return false;
    }
}
