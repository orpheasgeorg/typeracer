package org.orpheus.typeracer.room;

import lombok.Data;

import java.util.List;

@Data
public class Room {

    String code;
    List<String> players;
    RoomStatus status;
    String text;

}
