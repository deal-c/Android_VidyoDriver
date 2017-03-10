package com.esoon.vidyo.api.other.impl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.esoon.vidyo.CallingActivity;
import com.esoon.vidyo.VideoActivity;
import com.esoon.vidyo.api.other.ESClientCallBackMsg;
import com.esoon.vidyo.api.other.ESClientGetNeworkTrafficStat;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;
import com.vidyo.utils.Contants;
import com.vidyo.utils.EnterRoomHttp;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/8.
 */

public class ESClientCallBackMsgImpl implements ESClientCallBackMsg {
    private static final String TAG = "ESClientCallBackMsgImpl";
    Handler message_handler;
    VidyoSampleApplicationkevin app;
    int usedCamera = 1;
    StringBuffer message;


    private boolean doRender = false;
    public static final int CALL_ENDED = 0;
    public static final int MSG_BOX = 1;
    public static final int CALL_RECEIVED = 2;
    public static final int CALL_STARTED = 3;
    public static final int SWITCH_CAMERA = 4;
    public static final int LOGIN_SUCCESSFUL = 5;
    public static final int LIBRARY_STARTED = 6;
    public static final int Event_Group_Chat = 7;
    public static final int Event_ShowBand = 10;

    String startMsg;
    String endMsg;

    @Override
    public String esclientHandler(final Activity activity) {


        message_handler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();

                switch (msg.what) {
                    case LIBRARY_STARTED:

                        app.DisableAutoLogin();
                        break;

                    case CALL_STARTED:
                    /*    startMsg="video has already start";*/
                        Intent intent = new Intent("callstart");
                        intent.putExtra("yaner", "发送广播");
                        activity.sendBroadcast(intent);
                        startMsg="video start";
                        app.StartConferenceMedia();
                        app.SetPreviewModeON(true);
                        app.SetCameraDevice(usedCamera);
                        app.DisableShareEvents();
                        startDevices();
                        break;

                    case CALL_ENDED:
                        stopDevices();
                        app.RenderRelease();
                        endMsg = "video end";
                        break;

                    case MSG_BOX:
                        message = new StringBuffer(b.getString("text"));
                        break;

                    case SWITCH_CAMERA:
                        String whichCamera = (String) (msg.obj);
                        boolean isFrontCam = whichCamera.equals("FrontCamera");
                        // Log.d(VidyoSampleApplication.TAG, "Got camera switch = " + whichCamera);

                        // switch to the next camera, force settings are per device.
                        // sample does not get this values
                        //	bcCamera.switchCamera(isFrontCam, false, 0, false, false);
                        break;

                    case LOGIN_SUCCESSFUL:

                        break;
                }
            }
        };

        app = (VidyoSampleApplicationkevin) activity.getApplication();
        app.setHandler(message_handler);


        return null;
    }

    @Override
    public String onPartyEventReceived() {

        return null;
    }

    @Override
    public String onInviteEventReceived() {
        return null;
    }

    @Override
    public String onMessageEventReceived() {
        return null;
    }

    @Override
    public String onMessageEventJoinProgress() {


        return null;
    }

    @Override
    public String onMessageEventConferenceActive(final Activity activity) {

        Log.e(TAG, "broadcast success" + startMsg);


        return startMsg;
    }

    @Override
    public String onMessageEventConferenceEnded() {
        Log.e(TAG, "end video");
        return endMsg;
    }

    @Override
    public String onMessageEventOnOther() {
        return null;
    }

    void startDevices() {
        doRender = true;
    }

    void stopDevices() {
        doRender = false;
    }
}
