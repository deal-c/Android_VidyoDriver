package com.esoon.vidyo.api.room.impl;

import android.app.Activity;

import com.esoon.vidyo.api.room.ESClientUninitialize;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * Created by Administrator on 2017/2/16.
 */

public class ESClientUninitializeImpl implements ESClientUninitialize {


    @Override
    public boolean esclientUninit(Activity activity) {
        VidyoSampleApplicationkevin app;

        app = (VidyoSampleApplicationkevin) activity.getApplication();
      //  Tools.SaveConfigData(activity, "roomid", "");

        app.DisableAllVideoStreams();
        app.Dispose();
        app.uninitialize();
        activity.finish();
        return false;
    }
}
