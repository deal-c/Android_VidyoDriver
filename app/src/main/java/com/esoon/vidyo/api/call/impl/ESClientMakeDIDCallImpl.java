package com.esoon.vidyo.api.call.impl;

import android.util.Log;

import com.esoon.pojo.CallingManagerMsg;
import com.esoon.pojo.CallingMsg;
import com.esoon.vidyo.api.call.ESClientMakeDIDCall;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/21.
 */

public class ESClientMakeDIDCallImpl implements ESClientMakeDIDCall {
    private static final String TAG = "videoactivity";
    boolean flag = false;
    JSONObject object;
    JSONObject  room;
    String  roomKey;
    @Override
    public String esclientMakeDiDCall(CallingManagerMsg callingMsg) {
        RequestParams requestParams = new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/createRoom");
        Gson gson = new Gson();
        String calling = gson.toJson(callingMsg);
        requestParams.addBodyParameter("", calling);

        Log.e(TAG, callingMsg.toString() + "传递消息");

        try {
            object= x.http().postSync(requestParams,JSONObject.class);
            // int   statusCode= object.getInt("statusCode");
            room=object.getJSONObject("room");
            roomKey=room.getString("roomKey");


        }catch (Throwable throwable){
            Log.e(TAG,throwable.toString()+"121");
            Log.e(TAG,throwable.getMessage()+"121");
            Log.e(TAG,throwable.getLocalizedMessage()+"1212");
            throwable.printStackTrace();
        }

        Log.e(TAG, room+"Something  wrong, filesDir is null");
        Log.e(TAG,roomKey+"Something  wrong, filesDir is null");
        Log.e(TAG, room+"Something  wrong, filesDir is null");
        return roomKey;
    }
}
