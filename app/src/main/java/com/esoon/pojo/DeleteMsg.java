package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/15.
 */

public class DeleteMsg {
   private  int roomId;
   private String userId;
    private int deletetype;

    public DeleteMsg(int roomId, String userId, int deletetype) {
        this.roomId = roomId;
        this.userId = userId;
        this.deletetype = deletetype;
    }

    public DeleteMsg(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDeletetype() {
        return deletetype;
    }

    public void setDeletetype(int deletetype) {
        this.deletetype = deletetype;
    }
}
