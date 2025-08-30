package com.huyphan.hahoot.test.unit.quiz.gameplay;

import com.huyphan.hahoot.quiz.gameplay.core.model.Participant;
import com.huyphan.hahoot.quiz.gameplay.core.model.ParticipantStatus;

import java.util.UUID;

public class DummyParticipant implements Participant {
    @Override
    public ParticipantStatus getStatus() {
        return ParticipantStatus.ONLINE_AND_DO_NOTHING;
    }

    @Override
    public void setStatusToPlaying() {

    }

    @Override
    public UUID getId() {
        return null;
    }
}
