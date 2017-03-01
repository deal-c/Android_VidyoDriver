package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/21.
 */

public class CallingManagerMsg {

    String  userId;
    String  userData;
    String  manageId;
    String  subMediaType;
int  callType;

    public CallingManagerMsg(int callType, String subMediaType, String manageId, String userId) {
        this.callType = callType;
        this.subMediaType = subMediaType;
        this.manageId = manageId;
        this.userId = userId;
    }

    @Override

    public String toString() {
        return "CallingManagerMsg{" +
                "userId='" + userId + '\'' +
                ", userData='" + userData + '\'' +
                ", manageId='" + manageId + '\'' +
                ", subMediaType='" + subMediaType + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getManageId() {
        return manageId;
    }

    public void setManageId(String manageId) {
        this.manageId = manageId;
    }

    public String getSubMediaType() {
        return subMediaType;
    }

    public void setSubMediaType(String subMediaType) {
        this.subMediaType = subMediaType;
    }

    public CallingManagerMsg(String userId, String manageId, String subMediaType) {
        this.userId = userId;
        this.manageId = manageId;
        this.subMediaType = subMediaType;
    }

    public CallingManagerMsg(String userId, String userData, String manageId, String subMediaType) {
        this.userId = userId;
        this.userData = userData;
        this.manageId = manageId;
        this.subMediaType = subMediaType;
    }

    public CallingManagerMsg(String subMediaType) {
        this.subMediaType = subMediaType;
    }
}
