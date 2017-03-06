package com.esoon.vidyo.api.other.impl;

import android.app.Activity;

import com.esoon.vidyo.api.other.ESClientSwitchAudioDevice;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * Created by Administrator on 2017/3/2.
 */

public class ESClientSwitchAudioDeviceImpl  implements ESClientSwitchAudioDevice{
    VidyoSampleApplicationkevin app;
    @Override
    public boolean SwitchAudioDevice(Activity   activity,boolean    flag) {
        app = (VidyoSampleApplicationkevin) activity.getApplication();
        app.AutoStartSpeaker(flag);

        return false;
    }
}
