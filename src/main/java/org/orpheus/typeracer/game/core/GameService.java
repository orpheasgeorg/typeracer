package org.orpheus.typeracer.game.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.game.DTO.PlayerReadyRequest;
import org.orpheus.typeracer.game.GameSession;
import org.orpheus.typeracer.room.Room;
import org.orpheus.typeracer.room.core.RoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GameService {

    private final Map<String, GameSession> sessions = new HashMap<>();
    private final RoomService roomService;

    public boolean playerReady(PlayerReadyRequest playerReadyRequest) {
        String roomCode = playerReadyRequest.getRoomCode();
        String username = playerReadyRequest.getUsername();

        Room room = roomService.getRoom(roomCode);
        if (!room.getPlayers().contains(username)) {
            throw new RuntimeException("User " + username + " does not belong to room " + roomCode);
        }

        if (!sessions.containsKey(roomCode)) {
            GameSession gameSession = new GameSession();
            gameSession.setRoomCode(roomCode);
            gameSession.setReadyPlayers(new ArrayList<>());
            gameSession.setPlayerProgress(new HashMap<>());
            sessions.put(roomCode, gameSession);
        }

        GameSession session = sessions.get(roomCode);
        session.getReadyPlayers().add(username);

        if (session.getReadyPlayers().size() == room.getPlayers().size() && room.getPlayers().size() == 4) {
            return true;
        }
        return false;
    }
}
