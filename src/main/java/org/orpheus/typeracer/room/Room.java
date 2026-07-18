package org.orpheus.typeracer.room;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room {

    String code;
    List<String> players = new ArrayList<>();
    RoomStatus status;
    String text;

}
