package com.huyphan.hahoot.quiz.gameplay.service;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.huyphan.hahoot.quiz.gameplay.model.HahootGame;

import lombok.AllArgsConstructor;

@Service
@Scope
@AllArgsConstructor
public class GameplayService {

    public void startGame() {
        HahootGame.create(null, null, UUID.randomUUID());

    }

    public void nextQuiz() {

        // Logic to proceed to the next quiz
        System.out.println("Proceeding to the next quiz");
    }

    public void showAnswer() {
        // Logic to show the answer
        System.out.println("Showing the answer");
    }

    public void endGame() {
        // Logic to end the game
        System.out.println("Game ended");
    }

}
