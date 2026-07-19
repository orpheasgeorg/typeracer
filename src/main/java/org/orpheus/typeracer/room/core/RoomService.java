package org.orpheus.typeracer.room.core;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.room.Room;
import org.orpheus.typeracer.room.RoomStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
public class RoomService {
    private final Map<String, Room> rooms = new HashMap<>();

    public Room createRoom(String username) {

        String code = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Room room = new Room();
        room.setCode(code);
        room.getPlayers().add(username);
        room.setStatus(RoomStatus.WAITING);
        rooms.put(code, room);
        return room;
    }

    public Room joinRoom(String username, String code) {
        if (rooms.containsKey(code)) {
            Room room = rooms.get(code);
            if (room.getPlayers().size() < 4) {
                room.getPlayers().add(username);
                rooms.put(code, room);
                return room;
            } else {
                throw new RuntimeException("Room is full");
            }
        } else {
            throw new RuntimeException("Room not found");
        }
    }

    //GameService method
    public Room getRoom(String code) {
        return rooms.get(code);
    }
}
