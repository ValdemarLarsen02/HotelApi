package app.dto;

import java.util.ArrayList;
import java.util.List;

public class HotelDTO {
    private int id;
    private String name;
    private String address;
    private List<RoomDTO> rooms = new ArrayList<>(); // ✅ Initialiser rooms som en tom liste

    public HotelDTO() {}

    public HotelDTO(int id, String name, String address, List<RoomDTO> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rooms = (rooms != null) ? rooms : new ArrayList<>(); // ✅ Sikrer at rooms aldrig er null
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<RoomDTO> getRooms() { return rooms; }
    public void setRooms(List<RoomDTO> rooms) { this.rooms = (rooms != null) ? rooms : new ArrayList<>(); }
}
