package com.esoon.vidyo.api.other.impl;

import android.app.Activity;
import android.util.Log;

import com.esoon.vidyo.api.other.ESClientGetNeworkTrafficStat;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * 获取网络延迟数
 */

public class ESClientGetNeworkTrafficStatImpl implements ESClientGetNeworkTrafficStat {
    String  showInfo;
    VidyoSampleApplicationkevin app;
    private String      TAG="ESClientGetNework";

    @Override
    public String EsclientGetNeworkTrafficStat(Activity activity) {

        app = (VidyoSampleApplicationkevin) activity.getApplication();
        showInfo=app.getBandInfo();
        Log.e(TAG,"showInfo is  :"+showInfo);

        return showInfo;
    }
}
