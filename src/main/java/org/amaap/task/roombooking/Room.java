package org.amaap.task.roombooking;

import java.util.ArrayList;
import java.util.List;

public class Room {

    static List<Room> rooms = new ArrayList<>();

    private String roomName;
    private boolean status;

    public Room() {
    }

    public Room(String roomName, boolean status) {
        this.roomName = roomName;
        this.status = status;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
