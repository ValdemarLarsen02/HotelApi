package app.routes;

import app.controller.HotelController;
import app.controller.RoomController;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {
    public static void setupRoutes() {
        path("/hotel", () -> {
            get(HotelController::getAllHotels);
            post(HotelController::createHotel);
            path("{id}", () -> {
                get(HotelController::getHotelById);
                put(HotelController::updateHotel);
                delete(HotelController::deleteHotel);
                path("/rooms", () -> get(HotelController::getRoomsForHotel));
                path("/room", () -> post(RoomController::addRoomToHotel));
            });
        });
        path("/hotel/{hotelId}/room/{roomId}", () -> delete(RoomController::removeRoomFromHotel));
    }
}
