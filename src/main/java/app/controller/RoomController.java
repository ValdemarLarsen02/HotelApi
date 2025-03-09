package app.controller;

import app.dto.RoomDTO;
import app.model.Hotel;
import app.model.Room;
import app.service.HotelService;
import app.service.HotelServiceImpl;
import app.service.RoomConverter;
import io.javalin.http.Context;

public class RoomController {
    private static final HotelService hotelService = new HotelServiceImpl();

    public static void addRoomToHotel(Context ctx) {
        int hotelId = Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelService.getHotelById(hotelId);

        if (hotel == null) {
            ctx.status(404).result("Hotel not found");
            return;
        }

        RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);
        Room room = RoomConverter.toEntity(roomDTO);
        hotelService.addRoom(hotel, room);
        ctx.status(201).json(RoomConverter.toDTO(room));
    }

    public static void removeRoomFromHotel(Context ctx) {
        int hotelId = Integer.parseInt(ctx.pathParam("hotelId"));
        int roomId = Integer.parseInt(ctx.pathParam("roomId"));

        Hotel hotel = hotelService.getHotelById(hotelId);
        if (hotel == null) {
            ctx.status(404).result("Hotel not found");
            return;
        }

        Room roomToRemove = hotel.getRooms().stream()
                .filter(r -> r.getId() == roomId)
                .findFirst()
                .orElse(null);

        if (roomToRemove == null) {
            ctx.status(404).result("Room not found");
            return;
        }

        hotelService.removeRoom(hotel, roomToRemove);
        ctx.json(RoomConverter.toDTO(roomToRemove));
    }
}
