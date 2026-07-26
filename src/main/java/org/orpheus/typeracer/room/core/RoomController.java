package org.orpheus.typeracer.room.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.game.core.GameService;
import org.orpheus.typeracer.room.Room;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final GameService gameService;

    @PostMapping("/create")
    public Room createRoom() {
        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        return roomService.createRoom(username);
    }

    @PostMapping("/join/{code}")
    public Room joinRoom(@PathVariable String code) {
        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        Room room = roomService.joinRoom(username, code);
        gameService.registerUserRoom(username, code);
        return room;
    }

}
