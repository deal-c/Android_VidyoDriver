package com.pojo;

/**
 * Created by Administrator on 2017/1/22.
 */

public class Mymessage {

   private String userId;
    private String subMeidaType;
    private String queueType;
    private String callType;
    private RoomMsg roomMsg;
    public Mymessage(){}

    public Mymessage(String userId, String subMeidaType, String queueType, String callType, RoomMsg roomMsg) {
        this.userId = userId;
        this.subMeidaType = subMeidaType;
        this.queueType = queueType;
        this.callType = callType;
        this.roomMsg = roomMsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RoomMsg getRoomMsg() {
        return roomMsg;
    }

    public void setRoomMsg(RoomMsg roomMsg) {
        this.roomMsg = roomMsg;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getSubMeidaType() {
        return subMeidaType;
    }

    public void setSubMeidaType(String subMeidaType) {
        this.subMeidaType = subMeidaType;
    }
}
