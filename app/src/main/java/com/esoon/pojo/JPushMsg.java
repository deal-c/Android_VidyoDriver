package com.esoon.pojo;

import java.util.Set;

/**
 * Created by Administrator on 2017/3/9.
 */

public class JPushMsg {
    @Override
    public String toString() {
        return "JPushMsg{" +
                "userId='" + userId + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", uid='" + uid + '\'' +
                ", tag=" + tag +
                ", alias='" + alias + '\'' +
                ", platform=" + platform +
                ", pushCompany='" + pushCompany + '\'' +
                '}';
    }

    private   String  userId;
    private   String  registrationId;
    private  String  uid;
    private String tag;
    private  String  alias;
    private  int  platform;
    private String  pushCompany;

    public JPushMsg(String userId, String pushCompany, int platform, String alias, String tag, String uid, String registrationId) {
        this.userId = userId;
        this.pushCompany = pushCompany;
        this.platform = platform;
        this.alias = alias;
        this.tag = tag;
        this.uid = uid;
        this.registrationId = registrationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPushCompany() {
        return pushCompany;
    }

    public void setPushCompany(String pushCompany) {
        this.pushCompany = pushCompany;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
