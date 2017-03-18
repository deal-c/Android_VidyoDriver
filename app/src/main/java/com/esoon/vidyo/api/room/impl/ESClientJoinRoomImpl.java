package com.esoon.vidyo.api.room.impl;

import android.app.Activity;
import android.util.Log;


import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.room.ESClientJoinRoom;

import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * 加入房间
 */

public class ESClientJoinRoomImpl implements ESClientJoinRoom {
    private static final String TAG = "ESClientJoinRoomImpl";
    VidyoSampleApplicationkevin app;
    @Override
    public boolean joinRom(Activity activity,String roomKey,String  userName) {
        Log.e(TAG,"ESClientJoinRoom start");
         app=(VidyoSampleApplicationkevin)activity.getApplication();
        app.GuestLogin(VidyoUtils.VidyoPortalInfo,roomKey,userName,"");
        Log.e(TAG,"ESClientJoinRoom success");
        return false;
    }

    @Override
    public void joinRomCallBack(boolean flag) {

    }


}
