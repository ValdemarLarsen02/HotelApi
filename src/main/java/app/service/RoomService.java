package app.service;

import app.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private final List<Room> rooms = new ArrayList<>();

    public List<Room> getAllRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
