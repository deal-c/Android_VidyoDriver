package com.pojo;

/**
 * Created by Administrator on 2017/2/15.
 */

public class RoomMsg {
    private String roomName;
    private String roomsubject;
   private String roomlocation;

    public RoomMsg(String roomName, String roomsubject, String roomlocation) {
        this.roomName = roomName;
        this.roomsubject = roomsubject;
        this.roomlocation = roomlocation;

    }
    public RoomMsg(){}

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomsubject() {
        return roomsubject;
    }

    public void setRoomsubject(String roomsubject) {
        this.roomsubject = roomsubject;
    }

    public String getRoomlocation() {
        return roomlocation;
    }

    public void setRoomlocation(String roomlocation) {
        this.roomlocation = roomlocation;
    }
}
