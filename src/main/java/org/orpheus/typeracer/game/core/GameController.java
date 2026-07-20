package org.orpheus.typeracer.game.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.game.DTO.PlayerProgressRequest;
import org.orpheus.typeracer.game.DTO.PlayerProgressResponse;
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
        boolean allReady = gameService.playerReady(playerReadyRequest);
        if (allReady) {
            messagingTemplate.convertAndSend(
                    "/topic/room." + playerReadyRequest.getRoomCode(),
                    "game.start"
            );
        }
    }

    //mporei na prepei na ginei elegxos kai apo ton server tou ti stelnei o client gia to progress
    @MessageMapping("/player.progress")
    public void updateProgress(PlayerProgressRequest playerProgressRequest){
        PlayerProgressResponse playerProgressResponse = new PlayerProgressResponse();
        playerProgressResponse.setUsername(playerProgressRequest.getUsername());
        playerProgressResponse.setProgress(playerProgressRequest.getTypedText());
        messagingTemplate.convertAndSend(
                "/topic/room." + playerProgressRequest.getRoomCode(),
                playerProgressResponse
        );
    }

}
