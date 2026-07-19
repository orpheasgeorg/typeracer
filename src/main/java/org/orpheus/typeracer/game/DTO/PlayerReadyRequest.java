package org.orpheus.typeracer.game.DTO;

import lombok.Data;

@Data
public class PlayerReadyRequest {
    String roomCode;
    String username;
}
