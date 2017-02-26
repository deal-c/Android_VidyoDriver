package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/26.
 */

public class ReturnMsg {
   private String  roomKey;
    private String  roomId;

    public ReturnMsg(String roomKey, String roomId) {
        this.roomKey = roomKey;
        this.roomId = roomId;
    }
    public ReturnMsg(){}
    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
