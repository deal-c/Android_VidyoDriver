package com.esoon.vidyo.api.other.impl;

import android.app.Activity;

import com.esoon.vidyo.api.other.ESClientMuteAudio;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * Created by Administrator on 2017/2/27.
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
