package org.solvd.company.designPatterns.proxy;

import org.solvd.company.domain.office.Room;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Proxy Design Pattern Example");
        Room room = new Room();
        room.setRoomNumber("101");
        room.setCapacity(10);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        room.setAvailable(true);
        ArrangeProxy arrangeProxy = new ArrangeProxy(rooms);
        ArrangeMeeting arrangeMeeting = new ArrangeMeeting();
        arrangeProxy.meeting();
    }
}
