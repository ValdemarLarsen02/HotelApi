package app.dto;

public class RoomDTO {
    private int id;
    private int number;
    private double price;

    // ✅ Jackson kræver en tom constructor
    public RoomDTO() {}

    public RoomDTO(int id, int number, double price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
