package com.esoon.vidyo.api.room.impl;

import android.app.Activity;
import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.WindowManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.esoon.R;
import com.esoon.utils.EnterRoomHttp;
import com.esoon.vidyo.api.room.ESClientInitialize;


import com.esoon.vidyosample.VidyoSampleApplicationkevin;
import com.vidyo.LmiDeviceManager.*;

import android.hardware.SensorManager;

public class ESClientInitializeImpl implements ESClientInitialize,
        LmiDeviceManagerView.Callback,
        SensorEventListener {
    /**
     * 初始化
     */
    private static final String TAG = "ESClientInitializeImpl";
    private boolean doRender = false;
    private LmiDeviceManagerView bcView; // new 2.2.2
    private static boolean loginStatus = false;
    private boolean cameraPaused = false;
    private boolean cameraStarted = false;
    final float degreePerRadian = (float) (180.0f / Math.PI);
    public static final int CALL_ENDED = 0;
    public static final int MSG_BOX = 1;
    public static final int CALL_RECEIVED = 2;
    public static final int CALL_STARTED = 3;
    public static final int SWITCH_CAMERA = 4;
    public static final int LOGIN_SUCCESSFUL = 5;
    public static final int LIBRARY_STARTED = 6;
    final int ORIENTATION_UP = 0;
    final int ORIENTATION_DOWN = 1;
    final int ORIENTATION_LEFT = 2;
    final int ORIENTATION_RIGHT = 3;
    private float[] mGData = new float[3];
    private float[] mMData = new float[3];
    private float[] mR = new float[16];
    private float[] mI = new float[16];
    private float[] mOrientation = new float[3];
    final int DIALOG_LOGIN = 0;
    final int DIALOG_JOIN_CONF = 3;
    final int DIALOG_MSG = 1;
    final int DIALOG_CALL_RECEIVED = 2;
    final int FINISH_MSG = 4;
    VidyoSampleApplicationkevin app;
    Handler message_handler;
    StringBuffer message;
    private int currentOrientation;
    private SensorManager sensorManager;
    public static boolean isHttps = false;
    int usedCamera = 1;


    private boolean flag = false;


    @Override
    public boolean ESClientInitialize(final Activity activity, String crtpath) {

        message_handler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                switch (msg.what) {
                    case LIBRARY_STARTED:
                        app.DisableAutoLogin();
                        break;

                    case CALL_STARTED:
                        app.StartConferenceMedia();
                        app.SetPreviewModeON(true);
                        app.SetCameraDevice(usedCamera);
                        app.DisableShareEvents();
                        startDevices();
                        break;

                    case CALL_ENDED:
                        stopDevices();

                        app.RenderRelease();
                        break;

                    case MSG_BOX:
                        message = new StringBuffer(b.getString("text"));

                        break;

                    case SWITCH_CAMERA:
                        String whichCamera = (String)(msg.obj);
                        boolean isFrontCam = whichCamera.equals("FrontCamera");


                        // switch to the next camera, force settings are per device.
                        // sample does not get this values
                        //	bcCamera.switchCamera(isFrontCam, false, 0, false, false);
                        break;

                    case LOGIN_SUCCESSFUL:

                        break;
                }
            }
        };

//	  app = new VidyoSampleApplication(message_handler);


        app = (VidyoSampleApplicationkevin)activity.getApplication();
     //   app.setHandler(message_handler);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);// get the full screen size from android
          /*activity.setContentView(R.layout.conference);*/
        bcView = new LmiDeviceManagerView(activity, this);
        View C = activity.findViewById(R.id.glsurfaceview);
        ViewGroup parent = (ViewGroup) C.getParent();
        int index = parent.indexOfChild(C);
        parent.removeView(C);
        parent.addView(bcView, index);
        usedCamera = 1;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        String dialogMessage;
        currentOrientation = -1;

        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        Sensor gSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this, gSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        if (netInfo == null || !netInfo.isConnected()) {
            dialogMessage = new String("Network Unavailable!\n" + "Check network connection.");
            activity.showDialog(FINISH_MSG);

            return flag;
        } else if (app.initialize(crtpath, activity) == false) {
            dialogMessage = new String("Initialization Failed!\n" + "Check network connection.");
            activity.showDialog(FINISH_MSG);

            return flag;
        }
        if (!loginStatus) {
            loginStatus = true;
            app.HideToolBar(false);
            app.SetEchoCancellation(true);
        }
        Log.d(TAG, "ESClientInitialize  success");
        flag = true;
        return flag;
    }

    @Override
    public boolean ESCLientInitalizeCallback(boolean flag) {
        if (flag){
Log.e(TAG,"Init success");

        }else {
            Log.e(TAG,"Init fail");
        }

        return flag;
    }



    void startDevices() {
        doRender = true;
    }

    void stopDevices() {
        doRender = false;
    }

    private void pauseCall() {
        this.bcView.onPause();
    }


    public void LmiDeviceManagerViewRender() {

            app.Render();
    }

    public void LmiDeviceManagerViewResize(int width, int height) {
        app.Resize(width, height);
    }

    public void LmiDeviceManagerViewRenderRelease() {
        app.RenderRelease();
    }

    public void LmiDeviceManagerViewTouchEvent(int id, int type, int x, int y) {
        app.TouchEvent(id, type, x, y);
    }

    public int LmiDeviceManagerCameraNewFrame(byte[] frame, String fourcc,
                                              int width, int height, int orientation, boolean mirrored) {
        return app.SendVideoFrame(frame, fourcc, width, height, orientation, mirrored);
    }

    public int LmiDeviceManagerMicNewFrame(byte[] frame, int numSamples,
                                           int sampleRate, int numChannels, int bitsPerSample) {
        return app.SendAudioFrame(frame, numSamples, sampleRate, numChannels,
                bitsPerSample);
    }

    public int LmiDeviceManagerSpeakerNewFrame(byte[] frame, int numSamples,
                                               int sampleRate, int numChannels, int bitsPerSample) {
        return app.GetAudioFrame(frame, numSamples, sampleRate, numChannels,
                bitsPerSample);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {


    }


}
