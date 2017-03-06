package com.esoon.vidyo.api.call.impl;

import android.app.Activity;

import com.esoon.vidyo.api.call.ESClientGetVideoNumParticipants;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * Created by Administrator on 2017/3/6.
 */

public class ESClientGetVideoNumParticipantsImpl    implements ESClientGetVideoNumParticipants {
    int num;

    @Override
    public int GetVideoNumParticipants(Activity activity) {
        VidyoSampleApplicationkevin app;

        app = (VidyoSampleApplicationkevin) activity.getApplication();
        num=app.RequestGetNumParticipants();
        return num;
    }
}
