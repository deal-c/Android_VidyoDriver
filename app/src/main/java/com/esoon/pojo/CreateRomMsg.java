package com.esoon.pojo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/22.
 */

public class CreateRomMsg {
private String  userId;
    private room    room;
    private int callType;
    private String  subMeidaType;
    private String  queueType;
    private String  managerId;
    private scheduleInfo    scheduleInfo;

    public CreateRomMsg(String userId, com.esoon.pojo.scheduleInfo scheduleInfo, String managerId, String subMeidaType, String queueType, int callType, com.esoon.pojo.room room) {
        this.userId = userId;
        this.scheduleInfo = scheduleInfo;
        this.managerId = managerId;
        this.subMeidaType = subMeidaType;
        this.queueType = queueType;
        this.callType = callType;
        this.room = room;
    }
}
