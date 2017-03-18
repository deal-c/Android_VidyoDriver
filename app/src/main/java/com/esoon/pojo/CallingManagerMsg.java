package com.esoon.pojo;

/**
 * Created by Administrator on 2017/2/21.
 */

public class CallingManagerMsg {

    String  userId;
    String  userData;
    String  managerId;
    String  subMediaType;
int  callType;

    public CallingManagerMsg(int callType, String subMediaType, String managerId, String userId) {
        this.callType = callType;
        this.subMediaType = subMediaType;
        this.managerId = managerId;
        this.userId = userId;
    }

    @Override

    public String toString() {
        return "CallingManagerMsg{" +
                "userId='" + userId + '\'' +
                ", userData='" + userData + '\'' +
                ", manageId='" + managerId + '\'' +
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
        return managerId;
    }

    public void setManageId(String manageId) {
        this.managerId = manageId;
    }

    public String getSubMediaType() {
        return subMediaType;
    }

    public void setSubMediaType(String subMediaType) {
        this.subMediaType = subMediaType;
    }

    public CallingManagerMsg(String userId, String manageId, String subMediaType) {
        this.userId = userId;
        this.managerId = manageId;
        this.subMediaType = subMediaType;
    }

    public CallingManagerMsg(String userId, String userData, String manageId, String subMediaType) {
        this.userId = userId;
        this.userData = userData;
        this.managerId = manageId;
        this.subMediaType = subMediaType;
    }

    public CallingManagerMsg(String subMediaType) {
        this.subMediaType = subMediaType;
    }
}
