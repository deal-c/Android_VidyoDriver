package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/15.
 */

public class RoomMsg {
    private int  roomId;
    private String roomName;
    private String roomSubject;

    public RoomMsg(int roomId, String roomName, String roomLocation, String roomSubject) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomLocation = roomLocation;
        this.roomSubject = roomSubject;
    }

    private String roomLocation;

    public RoomMsg(String roomName, String roomsubject, String roomlocation) {
        this.roomName = roomName;
        this.roomSubject = roomsubject;
        this.roomLocation = roomlocation;

    }
    public RoomMsg(){}

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
