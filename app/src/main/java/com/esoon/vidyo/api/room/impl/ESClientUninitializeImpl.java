package com.esoon.vidyo.api.room.impl;

import android.app.Activity;

import com.esoon.vidyo.api.room.ESClientUninitialize;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * 结束初始化
 */

public class ESClientUninitializeImpl implements ESClientUninitialize {


    @Override
    public boolean esClientUninit(Activity activity) {
        VidyoSampleApplicationkevin app;

        app = (VidyoSampleApplicationkevin) activity.getApplication();
        app.DisableAllVideoStreams();
        app.Dispose();
        app.uninitialize();
        activity.finish();
        return false;
    }
}
