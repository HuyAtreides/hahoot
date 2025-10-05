package com.huyphan.hahoot.gameplay.service;

import com.huyphan.hahoot.gameplay.model.HahootGame;
import com.huyphan.hahoot.gameplay.repository.GameplayInMemoryStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateGameService {
    private final GameplayInMemoryStorage gameplayInMemoryStorage;
    private final PreparedGameService preparedGameService;
    private final LobbyService lobbyService;

    @Transactional
    public void createGame(CreateGameCommand createGameCommand) {
        var lobbyId = createGameCommand.getLobbyId();
        var preparedGameId = createGameCommand.getPreparedGameId();
        var participants = lobbyService.getParticipantsInLobby(lobbyId);
        var quizzes = preparedGameService.getPreparedQuizzes(preparedGameId);

        var game = HahootGame.create(quizzes, participants);

        gameplayInMemoryStorage.saveGame(game);
        lobbyService.closeLobby(lobbyId);
        preparedGameService.freezePreparedGame(preparedGameId);
    }

}
