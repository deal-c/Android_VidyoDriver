package com.esoon.vidyo.api.other.impl;

import android.app.Activity;

import com.esoon.vidyo.api.other.ESClientMuteAudio;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * 开启/关闭话筒
 */

public  class ESClientMuteAudioImpl implements ESClientMuteAudio {

    VidyoSampleApplicationkevin app;
    @Override
    public boolean MuteAudio(boolean flag, Activity activity) {
        if (flag==true){

            app = (VidyoSampleApplicationkevin) activity.getApplication();
            app.AutoStartMicrophone(flag);
        }else {

            app.AutoStartMicrophone(flag);
        }


        return flag;
    }
}
