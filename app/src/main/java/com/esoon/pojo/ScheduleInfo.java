package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/20.
 */
public class ScheduleInfo {
    String  scheduleDate;
    public ScheduleInfo(){}
    public ScheduleInfo(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
    Userdata[]  users;
    Userdata    userData;

    public ScheduleInfo(String scheduleDate, Userdata userData, Userdata[] users) {
        this.scheduleDate = scheduleDate;
        this.userData = userData;
        this.users = users;
    }
}
