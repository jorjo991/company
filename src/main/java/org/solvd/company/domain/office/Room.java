package org.solvd.company.domain.office;

import java.security.PublicKey;
import java.util.Objects;

public class Room {

    private Long id;
    private Integer capacity;
    private Boolean available;
    private String roomNumber;

    public Room(Long id, int capacity, boolean available, String roomNumber) {
        this.id = id;
        this.capacity = capacity;
        this.available = available;
        this.roomNumber = roomNumber;
    }

    public Room() {

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) && Objects.equals(capacity, room.capacity) && Objects.equals(available, room.available) && Objects.equals(roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, available, roomNumber);
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
