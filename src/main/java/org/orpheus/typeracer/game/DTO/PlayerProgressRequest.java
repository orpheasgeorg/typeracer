package org.orpheus.typeracer.game.DTO;

import lombok.Data;

@Data
public class PlayerProgressRequest {
    String roomCode;
    String username;
    String typedText;
}
