package com.esoon.vidyo.api.other.impl;

import android.app.Activity;

import com.esoon.vidyo.api.other.ESClientSendMessage;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * Created by Administrator on 2017/2/27.
 */

public class ESClientSendMessageImpl implements ESClientSendMessage{

    VidyoSampleApplicationkevin app;
    @Override
    public boolean Sendchat(String userId, String messageInfo, Activity activity) {

        app = (VidyoSampleApplicationkevin) activity.getApplication();
        app.SendChat(messageInfo);
        return false;
    }
}
