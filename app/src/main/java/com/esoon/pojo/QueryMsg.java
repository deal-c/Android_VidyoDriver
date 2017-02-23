package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/20.
 */

public class QueryMsg {
    String  userId;
    int roomId;
    int getType;

    public QueryMsg(int getType) {
        this.getType = getType;
    }

    public QueryMsg(String userId, int getType) {
        this.userId = userId;
        this.getType = getType;
    }

    public QueryMsg(int getType, int roomId, String userId) {
        this.getType = getType;
        this.roomId = roomId;
        this.userId = userId;
    }

}
