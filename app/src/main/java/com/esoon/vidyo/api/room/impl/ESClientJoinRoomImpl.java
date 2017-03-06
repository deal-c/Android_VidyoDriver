package com.esoon.vidyo.api.room.impl;

import android.app.Activity;
import android.os.AsyncTask;

import com.esoon.utils.ICancelCall;
import com.esoon.utils.INetRequest;


import com.esoon.vidyo.api.room.ESClientJoinRoom;

import com.esoon.vidyosample.VidyoSampleApplicationkevin;
import com.vidyo.LmiDeviceManager.LmiVideoCapturer;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/12.
 */

public class ESClientJoinRoomImpl implements ESClientJoinRoom {
    StringBuffer serverString;
    StringBuffer usernameString;
    StringBuffer passwordString;
    VidyoSampleApplicationkevin app;
    @Override
    public boolean JoinRom(Activity activity,String roomKey) {
     app=(VidyoSampleApplicationkevin)activity.getApplication();
        app.GuestLogin("http://192.168.5.47",roomKey,"guest","");
        return false;
    }


}
