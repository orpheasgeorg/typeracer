package org.orpheus.typeracer.game;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class GameSession {
    String roomCode;
    List<String> readyPlayers;
    Map<String, Integer> playerProgress;
    LocalDateTime startTime;

}
