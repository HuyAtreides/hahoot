package com.huyphan.hahoot.test.unit.quiz.gameplay;

import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;
import com.huyphan.hahoot.quiz.gameplay.core.model.Participant;
import com.huyphan.hahoot.quiz.gameplay.core.model.ParticipantStatus;
import com.huyphan.hahoot.quiz.present.model.OptionSelectQuiz;
import com.huyphan.hahoot.user.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddParticipantTest {
    @Test
    void add_participant_to_in_progress_game_should_fail() {
        var participant = mock(Participant.class);
        var quiz = OptionSelectQuiz.builder().build();
        var game = HahootGame.create(List.of(quiz));

        game.start();
        assertThrows(IllegalStateException.class, () -> game.addParticipant(participant));
    }

    @Test
    void add_already_added_participant_to_game_should_fail() {
        var quiz = OptionSelectQuiz.builder().build();
        var game = HahootGame.create(List.of(quiz));
        var participant = mock(Participant.class);

        game.addParticipant(participant);

        assertThrows(IllegalStateException.class, () -> game.addParticipant(participant));
    }

    @Test
    void add_participant_with_playing_status_to_game_should_fail() {
        var quiz = OptionSelectQuiz.builder().build();
        var game = HahootGame.create(List.of(quiz));
        var participant = mock(Participant.class);

        when(participant.getStatus()).thenReturn(ParticipantStatus.I_AM_PLAYING);

        assertThrows(IllegalStateException.class, () -> game.addParticipant(participant));
    }

    @Test
    void add_participant_game_validly_should_success() {
        var quiz = OptionSelectQuiz.builder().build();
        var game = HahootGame.create(List.of(quiz));
        var participant = User.builder().build();

        game.addParticipant(participant);

        assertThat(game.getParticipants(), containsInAnyOrder(participant));
    }

}
