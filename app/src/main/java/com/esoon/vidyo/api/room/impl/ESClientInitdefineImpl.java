package com.esoon.vidyo.api.room.impl;

import android.app.Activity;

import com.esoon.vidyo.api.room.ESClientInitdefine;
import com.esoon.vidyo.api.room.ESClientInitialize;
import com.esoon.vidyo.api.room.impl.ESClientInitializeImpl;

/**
 * Created by Administrator on 2017/2/11.
 */

public class ESClientInitdefineImpl implements ESClientInitdefine {

    @Override
    public boolean ESClientInitdefine(Activity activity, String crtpath) {
        ESClientInitialize esClientInitialize=new ESClientInitializeImpl();


   return esClientInitialize.ESClientInitialize(activity,crtpath);
    }
}
