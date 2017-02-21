package com.esoon.pojo;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/15.
 */

public class CallingMsg {
    String userId;
    String subMediaType;
    String queueType;
    String userdata;



    public CallingMsg(String queueType, String subMediaType, String userId) {
        this.queueType = queueType;
        this.subMediaType = subMediaType;
        this.userId = userId;
    }

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

    public String getUserdata() {
        return userdata;
    }

    public void setUserdata(String userdata) {
        this.userdata = userdata;
    }
    public CallingMsg(){}
    public CallingMsg(String userId, String subMediaType, String queueType, String userdata) {
        this.userId = userId;
        this.subMediaType = subMediaType;
        this.queueType = queueType;
        this.userdata = userdata;
    }

    @Override
    public String toString() {
        return "CallingMsg{" +
                "userId='" + userId + '\'' +
                ", subMediaType='" + subMediaType + '\'' +
                ", queueType='" + queueType + '\'' +
                ", userdata='" + userdata + '\'' +
                '}';
    }
}
