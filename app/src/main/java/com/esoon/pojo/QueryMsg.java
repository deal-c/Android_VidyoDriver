package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/20.
 */

public class QueryMsg {
String  userId;
    int roomId;
    int getType;

    public QueryMsg(int roomId, int getType, String userId) {
        this.roomId = roomId;
        this.getType = getType;
        this.userId = userId;
    }
}
