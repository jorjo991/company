package org.solvd.company.designPatterns.proxy;

import org.solvd.company.domain.office.Room;

import java.util.List;

public class ArrangeProxy implements Arrange {
    ArrangeMeeting arrangeMeeting;
    List<Room> roomList;

    public ArrangeProxy(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public void meeting() {
        if (!roomList.isEmpty()) {
            for (Room room : roomList) {
                if (room.isAvailable()) {
                    if (arrangeMeeting == null) {
                        arrangeMeeting = new ArrangeMeeting();
                        arrangeMeeting.meeting();
                        break;
                    }
                }
            }
        }
    }
}
