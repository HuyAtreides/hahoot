package com.huyphan.hahoot.quiz.gameplay.infrastructure.config.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    @Bean
    public ExecutorService gameplayExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
