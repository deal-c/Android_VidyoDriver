package com.esoon.pojo;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/22.
 */

public class CreateRomMsg {


    @Override
    public String toString() {
        return "CreateRomMsg{" +
                "userId='" + userId + '\'' +
                ", room=" + room +
                ", callType=" + callType +
                ", subMediaType='" + subMediaType + '\'' +
                ", queueType='" + queueType + '\'' +
                ", managerId='" + managerId + '\'' +
                ", scheduleInfo=" + scheduleInfo +
                ", roomSchedule=" + roomSchedule +
                ", users=" + Arrays.toString(users) +
                ", userData=" + userData +
                '}';
    }

    private String  userId;
    private RoomMsg room;
    private int callType;
    private String  subMediaType;
    private String  queueType;
    private String  managerId;
    private ScheduleInfo scheduleInfo;
    private boolean roomSchedule;
    String[]   users=new   String[10];
    Userdata    userData;

    public CreateRomMsg(String userId, RoomMsg room, int callType, String subMediaType, String queueType, String managerId, ScheduleInfo scheduleInfo, boolean roomSchedule, String[] users) {
        this.userId = userId;
        this.room = room;
        this.callType = callType;
        this.subMediaType = subMediaType;
        this.queueType = queueType;
        this.managerId = managerId;
        this.scheduleInfo = scheduleInfo;
        this.roomSchedule = roomSchedule;
        this.users = users;
    }
}
