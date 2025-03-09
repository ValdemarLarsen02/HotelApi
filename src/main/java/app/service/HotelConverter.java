package app.service;

import app.dto.HotelDTO;
import app.dto.RoomDTO;
import app.model.Hotel;

import java.util.stream.Collectors;

public class HotelConverter {
    public static HotelDTO toDTO(Hotel hotel) {
        return new HotelDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getRooms().stream().map(RoomConverter::toDTO).collect(Collectors.toList())
        );
    }

    public static Hotel toEntity(HotelDTO dto) {
        Hotel hotel = new Hotel(dto.getId(), dto.getName(), dto.getAddress());
        hotel.setRooms(dto.getRooms().stream().map(RoomConverter::toEntity).collect(Collectors.toList()));
        return hotel;
    }
}
