package com.esoon.vidyo.api.room;

import android.app.Activity;

/**
 * Created by Administrator on 2017/2/12.
 */

public interface ESClientJoinRoom {

    public boolean  joinRom(Activity activity, String roomKey,String    userName);
    void joinRomCallBack(boolean  flag);
}
