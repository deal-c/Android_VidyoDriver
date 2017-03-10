package com.esoon.vidyo.api.room.impl;

import android.app.Activity;
import android.util.Log;


import com.esoon.vidyo.api.room.ESClientJoinRoom;

import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * 加入房间
 */

public class ESClientJoinRoomImpl implements ESClientJoinRoom {
    private static final String TAG = "ESClientJoinRoomImpl";
    VidyoSampleApplicationkevin app;
    @Override
    public boolean JoinRom(Activity activity,String roomKey) {
        Log.e(TAG,"ESClientJoinRoom start");
         app=(VidyoSampleApplicationkevin)activity.getApplication();
        app.GuestLogin("http://portal.esooncloud.com",roomKey,"guest","");
        Log.e(TAG,"ESClientJoinRoom success");
        return false;
    }


}
