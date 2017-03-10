package com.esoon.vidyo.api.room.impl;

import android.util.Log;

import com.esoon.pojo.CreateRomMsg;
import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.room.ESClientUpdateRoom;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 更新会议室
 */

public class ESClientUpdateRoomImpl implements ESClientUpdateRoom {
    private static final String TAG = "ESClientUpdateRomImpl";
    boolean flag=false;
    JSONObject object;
    JSONObject  room;

    @Override
    public boolean UpdateRoom(CreateRomMsg createRomMsg) {
        RequestParams requestParams=new RequestParams(VidyoUtils.queueinfo+"api/v1/video/vidyo/updateRoom");
        Gson gson=new Gson();
        String createMsg= gson.toJson(createRomMsg);
        Log.e(TAG, "updateroom    start!");
        requestParams.addBodyParameter("",createMsg);


        try {
            object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
           // room=object.getJSONObject("room");

            if(statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
            Log.e(TAG,"updateroom fail:");
            Log.e(TAG,"updateroom throwable is:"+throwable.toString());
        }
        Log.e(TAG,"updateroom  message  is:"+object.toString());
        Log.e(TAG,"updateroom    success!");
        return flag;
    }



}
