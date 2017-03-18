package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/15.
 */

public class Userdata {

    String userName;
    String userId;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Userdata(String userId) {
        this.userId = userId;
    }

    String address;
    public Userdata(){}
    public Userdata(String userName, String address, String userId) {
        this.userName = userName;
        this.address = address;
        this.userId = userId;
    }
}
