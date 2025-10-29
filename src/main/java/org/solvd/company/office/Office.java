package org.solvd.company.office;

import org.solvd.company.company.Address;

import java.util.List;

public class Office {

    private long id;
    private String name;
    private int capacity;
    private Address address;
    private List<Room> rooms;

    public Office(long id, String name, int capacity, Address address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", address=" + address +
                ", rooms=" + rooms +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
