package com.huyphan.hahoot.gameplay.test;

import com.huyphan.hahoot.gameplay.common.CommonConstraint;
import com.huyphan.hahoot.gameplay.common.ModelValidator;
import com.huyphan.hahoot.gameplay.model.GameStatus;
import com.huyphan.hahoot.gameplay.model.HahootGame;
import com.huyphan.hahoot.gameplay.model.Quiz;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CreateGameTest {

    @Test
    void create_game_with_null_quizzes_with_fail() {
        assertThrows(NullPointerException.class, () -> HahootGame.create(null, null));
    }

    @Test
    void create_game_with_participants_and_quizzes_but_contain_null_with_fail() {
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(null);

        var game = HahootGame.create(quizzes, null);
        ModelValidator.assertModelViolateConstraints(game, CommonConstraint.NOT_NULL);
    }

    @Test
    void create_game_with_empty_quizzes_with_fail() {
        var game = HahootGame.create(List.of(), List.of());
        ModelValidator.assertModelViolateConstraints(game, CommonConstraint.NOT_EMPTY);
    }


    @Test
    void create_game_correctly_should_success_with_correct_state() {
        var dummyQuiz = mock(Quiz.class);
        var game = HahootGame.create(List.of(dummyQuiz), List.of());
        ModelValidator.assertNotViolate(game);
        assertNotNull(game.getId());
        assertEquals(GameStatus.CREATED, game.getStatus());
        assertEquals(dummyQuiz, game.getCurrentQuiz());
    }
}