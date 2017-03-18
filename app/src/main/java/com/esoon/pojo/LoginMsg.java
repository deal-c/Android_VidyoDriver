package com.esoon.pojo;

/**
 * Created by Administrator on 2017/3/13.
 */

public class LoginMsg {
    String  userId;
    String  userPsd;
    String  userName;

    public LoginMsg(String userId, String userName, String userPsd) {
        this.userId = userId;
        this.userName = userName;
        this.userPsd = userPsd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
