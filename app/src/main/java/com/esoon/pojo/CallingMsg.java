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

    public CallingMsg(String userId, String queueType, String subMediaType) {
        this.userId = userId;
        this.queueType = queueType;
        this.subMediaType = subMediaType;
    }

    @Override
    public String toString() {
        return "CallingMsg{" +
                "queueType='" + queueType + '\'' +
                ", subMediaType='" + subMediaType + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public CallingMsg(String subMediaType, String queueType) {
        this.subMediaType = subMediaType;
        this.queueType = queueType;
    }
}
