package org.solvd.company.domain.office;

import org.solvd.company.domain.company.Address;

import java.util.List;
import java.util.Objects;

public class Office {

    private Long id;
    private String name;
    private Integer capacity;
    private Address address;
    private List<Room> rooms;

    public Office(long id, String name, int capacity, Address address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    public Office() {

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Objects.equals(id, office.id) && Objects.equals(name, office.name) && Objects.equals(capacity, office.capacity) && Objects.equals(address, office.address) && Objects.equals(rooms, office.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, address, rooms);
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
