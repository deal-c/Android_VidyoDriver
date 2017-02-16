package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/15.
 */

public class CallingMsg {
    String userId;
    String subMediaType;
    String queueType;
    Userdata userdata;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubMediaType() {
        return subMediaType;
    }

    public void setSubMediaType(String subMediaType) {
        this.subMediaType = subMediaType;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }
    public CallingMsg(){}
    public CallingMsg(String userId, String subMediaType, String queueType, Userdata userdata) {
        this.userId = userId;
        this.subMediaType = subMediaType;
        this.queueType = queueType;
        this.userdata = userdata;
    }
}
