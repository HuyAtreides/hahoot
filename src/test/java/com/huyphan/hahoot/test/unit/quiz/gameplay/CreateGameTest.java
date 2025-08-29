package com.huyphan.hahoot.test.unit.quiz.gameplay;

import com.huyphan.hahoot.quiz.gameplay.core.model.HahootGame;
import com.huyphan.hahoot.test.common.CommonConstraint;
import com.huyphan.hahoot.test.common.ModelValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateGameTest {

    @Test
    void create_game_with_empty_participants_with_fail() {
        var game = HahootGame.start(null, List.of());
        ModelValidator.assertModelViolateConstraints(game, CommonConstraint.NOT_EMPTY);
    }
}
