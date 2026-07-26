package org.orpheus.typeracer.game.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GameStartMessage {
    String event = "game.start";
    String text;
}
