package app.controller;

import app.dto.HotelDTO;
import app.model.Hotel;
import app.service.HotelService;
import app.service.HotelServiceImpl;
import app.service.HotelConverter;
import io.javalin.http.Context;

import java.util.List;
import java.util.stream.Collectors;

public class HotelController {
    private static final HotelService hotelService = new HotelServiceImpl();

    public static void getAllHotels(Context ctx) {
        List<HotelDTO> hotels = hotelService.getAllHotels()
                .stream()
                .map(HotelConverter::toDTO)
                .collect(Collectors.toList());
        ctx.json(hotels);
    }

    public static void getHotelById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelService.getHotelById(id);

        if (hotel == null) {
            ctx.status(404).result("Hotel not found");
            return;
        }

        ctx.json(HotelConverter.toDTO(hotel));
    }

    public static void getRoomsForHotel(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelService.getHotelById(id);

        if (hotel == null) {
            ctx.status(404).result("Hotel not found");
            return;
        }

        ctx.json(hotel.getRooms());
    }

    public static void createHotel(Context ctx) {
        HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
        Hotel hotel = HotelConverter.toEntity(hotelDTO);
        hotelService.createHotel(hotel);
        ctx.status(201).json(HotelConverter.toDTO(hotel));
    }

    public static void updateHotel(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Hotel existingHotel = hotelService.getHotelById(id);

        if (existingHotel == null) {
            ctx.status(404).result("Hotel not found");
            return;
        }

        HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
        existingHotel.setName(hotelDTO.getName());
        existingHotel.setAddress(hotelDTO.getAddress());

        hotelService.updateHotel(existingHotel);
        ctx.json(HotelConverter.toDTO(existingHotel));
    }

    public static void deleteHotel(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Hotel hotel = hotelService.getHotelById(id);

        if (hotel == null) {
            ctx.status(404).result("Hotel not found");
            return;
        }

        hotelService.deleteHotel(id);
        ctx.json(HotelConverter.toDTO(hotel));
    }
}
