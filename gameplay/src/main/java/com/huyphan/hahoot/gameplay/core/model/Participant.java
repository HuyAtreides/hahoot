package com.huyphan.hahoot.gameplay.core.model;

import java.util.UUID;

public interface Participant {
    UUID getId();

    ParticipantStatus getStatus();

    boolean equals();
}
