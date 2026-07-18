package org.orpheus.typeracer.room.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.room.Room;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public Room createRoom() {
        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        return roomService.createRoom(username);
    }

    @PostMapping("/join/{code}")
    public Room joinRoom(@PathVariable String code) {
        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        return roomService.joinRoom(username, code);
    }

}
