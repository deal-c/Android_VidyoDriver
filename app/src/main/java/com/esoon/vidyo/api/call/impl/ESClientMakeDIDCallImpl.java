package com.esoon.vidyo.api.call.impl;

import android.util.Log;

import com.esoon.pojo.CallingManagerMsg;
import com.esoon.pojo.CallingMsg;
import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.call.ESClientMakeDIDCall;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 拨打用户经理
 */

public class ESClientMakeDIDCallImpl implements ESClientMakeDIDCall {
    private static final String TAG = "ESClientMakeDIDCallImpl";
    boolean flag = false;
    JSONObject object;
    JSONObject  room;
    String  roomKey;
    @Override
    public String esclientMakeDiDCall(CallingManagerMsg callingMsg) {
        RequestParams requestParams = new RequestParams(VidyoUtils.queueinfo+"api/v1/video/vidyo/createRoom");
        Gson gson = new Gson();
        String calling = gson.toJson(callingMsg);
        requestParams.addBodyParameter("", calling);
        Log.e(TAG,"DIDCall  start");
        Log.e(TAG, callingMsg.toString() + "传递消息");

        try {
            object= x.http().postSync(requestParams,JSONObject.class);
            // int   statusCode= object.getInt("statusCode");
            Log.e(TAG,"DIDCall back msg is"+object);
           room=object.getJSONObject("room");
            roomKey=room.getString("roomKey");


        }catch (Throwable throwable){
            Log.e(TAG,"DIDCall  wrong   msg is:"+throwable.toString());
            Log.e(TAG,"DIDCall  wrong   msg is:"+throwable.getMessage());
            Log.e(TAG,throwable.getLocalizedMessage());
            throwable.printStackTrace();
        }

        Log.e(TAG,"DIDCall  sucess");
        return roomKey;
    }
}
