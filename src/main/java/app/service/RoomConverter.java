package app.service;

import app.dto.RoomDTO;
import app.model.Room;

public class RoomConverter {
    public static RoomDTO toDTO(Room room) {
        return new RoomDTO(room.getId(), room.getNumber(), room.getPrice());
    }


    public static Room toEntity(RoomDTO dto) {
        return new Room(dto.getId(), dto.getNumber(), dto.getPrice());
    }
}
