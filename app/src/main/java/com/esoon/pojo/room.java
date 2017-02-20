package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/15.
 */

public class room {
    private String roomName;
    private String roomSubject;
   private String roomLocation;

    public room(String roomName, String roomsubject, String roomlocation) {
        this.roomName = roomName;
        this.roomSubject = roomsubject;
        this.roomLocation = roomlocation;

    }
    public room(){}

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomsubject() {
        return roomSubject;
    }

    public void setRoomsubject(String roomsubject) {
        this.roomSubject = roomsubject;
    }

    public String getRoomlocation() {
        return roomLocation;
    }

    public void setRoomlocation(String roomlocation) {
        this.roomLocation = roomlocation;
    }
}
