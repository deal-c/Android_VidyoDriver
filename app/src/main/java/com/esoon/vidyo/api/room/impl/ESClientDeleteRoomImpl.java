package com.esoon.vidyo.api.room.impl;

import android.util.Log;

import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.room.ESClientDeleteRoom;
import com.google.gson.Gson;
import com.esoon.pojo.DeleteMsg;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 删除房间.
 */

public class ESClientDeleteRoomImpl implements ESClientDeleteRoom {
    boolean flag=false;
    private static final String TAG = "ESClientDeleteRoomImpl";
    @Override
    public boolean esClientDeleteRoom(DeleteMsg deleteMsg) {
        RequestParams requestParams=new RequestParams(VidyoUtils.NetPortalInfo+"api/v1/video/vidyo/deleteRoom");
       Gson gson=new Gson();
     String dMsg=gson.toJson(deleteMsg);
        requestParams.addBodyParameter("",dMsg);
        Log.e(TAG, "sending deleteMsg  :"+dMsg);
        Log.e(TAG, "deleteRom   start");
        Log.e(TAG, "requestParams   is"+requestParams.toString());
        try {
            JSONObject object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            if (statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
            Log.e(TAG, "deleteRom wrongMsg  is:"+throwable.toString());
        }
        Log.e(TAG, "deleteRom   success");
        return flag;
    }

    @Override
    public void esClientDeleteRoomCallBack(boolean flag) {

    }
}
