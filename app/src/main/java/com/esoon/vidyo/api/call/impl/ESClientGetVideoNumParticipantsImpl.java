package com.esoon.vidyo.api.call.impl;

import android.app.Activity;

import com.esoon.vidyo.api.call.ESClientGetVideoNumParticipants;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * 获取当前会议人数
 */

public class ESClientGetVideoNumParticipantsImpl    implements ESClientGetVideoNumParticipants {
    int num;

    @Override
    public int GetVideoNumParticipants(Activity activity) {
        VidyoSampleApplicationkevin app;
        app = (VidyoSampleApplicationkevin) activity.getApplication();
        num=app.RequestGetNumParticipants();//获得当前会议人数
        return num;
    }
}
