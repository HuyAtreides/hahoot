package com.huyphan.hahoot.test.unit.quiz.gameplay;

import com.huyphan.hahoot.quiz.gameplay.core.model.GameStatus;
import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;
import com.huyphan.hahoot.quiz.present.model.OptionSelectQuiz;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StartGameTest {

    @Test
    void start_game_but_game_is_finished_should_be_error() {
        var game = HahootGame.create(List.of(OptionSelectQuiz.builder().build()));
        game.start();
        game.end();

        assertThrows(IllegalStateException.class, game::start);
    }

    @Test
    void start_game_and_game_is_in_created_status_should_success() {
        var game = HahootGame.create(List.of(OptionSelectQuiz.builder().build()));
        assertDoesNotThrow(game::start);

        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
    }
}

