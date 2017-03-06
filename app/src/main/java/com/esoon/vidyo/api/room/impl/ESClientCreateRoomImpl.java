package com.esoon.vidyo.api.room.impl;

import android.util.Log;
import android.widget.Toast;

import com.esoon.pojo.CreateRomMsg;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/14.
 */

public class ESClientCreateRoomImpl implements ESClientCreateRoom {
    boolean flag=false;
    JSONObject object;
    JSONObject  room;
    String  roomKey;
    private static final String TAG = "ESClientCreateRoomImpl";
    @Override
    public String createroom(CreateRomMsg createRomMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/createRoom");
        Gson gson=new Gson();
      String createMsg= gson.toJson(createRomMsg);
        Log.e(TAG, "sending createRomMsg   :"+createMsg);
        Log.e(TAG, "createRoom   start");
        requestParams.addBodyParameter("",createMsg);

        try {
           object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            room=object.getJSONObject("room");
            roomKey=room.getString("roomKey");
            if(statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
            Log.e(TAG, "createRoom fail ");
            Log.e(TAG, "createRoom  throwable is"+throwable.toString());
        }

    /*    Log.e(TAG, room.toString()+"Something  wrong, filesDir is null");
        Log.e(TAG,roomKey+"Something  wrong, filesDir is null");
        Log.e(TAG, room+"Something  wrong, filesDir is null");*/
        Log.e(TAG, "createroom   success");
        return roomKey;
    }
}
