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

public class ESClientJoinRoomImpl implements ESClientJoinRoom,INetRequest,ICancelCall {
    StringBuffer serverString;
    StringBuffer usernameString;
    StringBuffer passwordString;
    VidyoSampleApplicationkevin app;
    @Override
    public boolean joinRom(Activity activity,String roomid) {
       app = (VidyoSampleApplicationkevin) activity.getApplication();
        app.Login("http://" +"192.168.5.47", "test3",
                "123456");
        app.HideToolBar(false);
        app.SetEchoCancellation(true);
      LmiVideoCapturer.onActivityPause();
        app.EnableAllVideoStreams();
  appSampleHttp.Arguments args = new appSampleHttp.Arguments("192.168.5.47","test3","123456",activity);
        AsyncTask<appSampleHttp.Arguments, Integer, appSampleHttp.Arguments> atHttpCalls = new appSampleHttp().execute( args );



        return false;
    }

    @Override
    public void OnCancelCall() {

    }

    @Override
    public void NetExecutePost(JSONObject result, int resultcode, int commandid) {

    }
}
