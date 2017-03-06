package com.esoon.vidyo.api.other.impl;

import android.app.Activity;

import com.esoon.vidyo.api.other.ESClientGetNeworkTrafficStat;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;

/**
 * Created by Administrator on 2017/3/2.
 */

public class ESClientGetNeworkTrafficStatImpl implements ESClientGetNeworkTrafficStat {
    String  showInfo;
    VidyoSampleApplicationkevin app;

    @Override
    public String EsclientGetNeworkTrafficStat(Activity activity) {

        app = (VidyoSampleApplicationkevin) activity.getApplication();
        showInfo=app.getBandInfo();


        return showInfo;
    }
}
