package app.service;

import app.model.Hotel;
import app.model.Room;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(int id);
    void createHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    void deleteHotel(int id);
    void addRoom(Hotel hotel, Room room);
    void removeRoom(Hotel hotel, Room room);
    List<Room> getRoomsForHotel(Hotel hotel);
}
