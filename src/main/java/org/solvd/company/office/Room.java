package org.solvd.company.office;

public class Room {

    private Long id;
    private int capacity;
    private boolean available;
    private  String roomNumber;

    public Room(Long id, int capacity, boolean available, String roomNumber) {
        this.id = id;
        this.capacity = capacity;
        this.available = available;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", available=" + available +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
