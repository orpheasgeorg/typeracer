package org.orpheus.typeracer.game.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.game.DTO.PlayerReadyRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GameService gameService;

    @MessageMapping("/game.ready")
    public void ready(PlayerReadyRequest playerReadyRequest) {
        gameService.playerReady(playerReadyRequest);

    }

}
