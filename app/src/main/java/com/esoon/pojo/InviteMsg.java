package com.esoon.pojo;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/8.
 */

public class InviteMsg {
    public Userdata[] getUsers() {
        return users;
    }

    public void setUsers(Userdata[] users) {
        this.users = users;
    }

    private Userdata[] users=new Userdata[20];
    private int  roomId;
    public InviteMsg(){};

    public InviteMsg(Userdata[] users, int roomId) {
        this.users = users;
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
