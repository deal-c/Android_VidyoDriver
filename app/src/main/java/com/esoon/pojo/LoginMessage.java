package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/9.
 */

public class LoginMessage {
   private String  userName;
    private String  userId;
private String  userPsd;

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public LoginMessage(String userName, String userId, String userPsd) {
        this.userName = userName;
        this.userId = userId;
        this.userPsd = userPsd;

    }

    public LoginMessage(){}

    public String getUserId() {
        return userId;
    }

    public LoginMessage(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



}
