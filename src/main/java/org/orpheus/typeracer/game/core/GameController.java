package org.orpheus.typeracer.game.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.game.DTO.GameStartMessage;
import org.orpheus.typeracer.game.DTO.PlayerProgressRequest;
import org.orpheus.typeracer.game.DTO.PlayerProgressResponse;
import org.orpheus.typeracer.game.DTO.PlayerReadyRequest;
import org.orpheus.typeracer.room.Room;
import org.orpheus.typeracer.room.core.RoomService;
import org.orpheus.typeracer.text.core.TextService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GameService gameService;
    private final TextService textService;
    private final RoomService roomService;

    @MessageMapping("/game.ready")
    public void ready(PlayerReadyRequest playerReadyRequest) {
        boolean allReady = gameService.playerReady(playerReadyRequest);
        if (allReady) {

            Room room = roomService.getRoom(playerReadyRequest.getRoomCode());
            room.setText(textService.getRandomText().getContent());

            GameStartMessage message = new GameStartMessage();
            message.setText(room.getText());

            messagingTemplate.convertAndSend(
                    "/topic/room." + playerReadyRequest.getRoomCode(),
                    message
            );
        }
    }

    @MessageMapping("/player.progress")
    public void updateProgress(PlayerProgressRequest playerProgressRequest){
        PlayerProgressResponse playerProgressResponse = new PlayerProgressResponse();
        playerProgressResponse.setUsername(playerProgressRequest.getUsername());

        Integer progress = gameService.calculateProgress(playerProgressRequest);

        if (gameService.isGameFinished(progress)) {
            messagingTemplate.convertAndSend(
                    "/topic/room." + playerProgressRequest.getRoomCode(),
                    "game.end:" + playerProgressRequest.getUsername()
            );
            return;
        }

        playerProgressResponse.setProgress(progress);
        messagingTemplate.convertAndSend(
                "/topic/room." + playerProgressRequest.getRoomCode(),
                playerProgressResponse
        );
    }



}
