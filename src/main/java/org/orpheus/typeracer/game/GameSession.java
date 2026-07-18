package org.orpheus.typeracer.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.orpheus.typeracer.security.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class GameSession {
    String roomCode;
    List<User> readyPlayers;
    Map<String, Integer> playerProgress;
    LocalDateTime startTime;
}
