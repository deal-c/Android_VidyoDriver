package com.esoon.vidyo.api.call.impl;

import android.util.Log;

import com.esoon.pojo.ReturnMsg;
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.google.gson.Gson;
import com.esoon.pojo.CallingMsg;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/15.
 */

public class ESClientMakeACDCallImpl implements ESClientMakeACDCall {
    private static final String TAG = "videoactivity";
    boolean flag = false;
    JSONObject object;
    JSONObject  room;
    String  roomKey;
    String  roomId;
    ReturnMsg   returnMsg=new   ReturnMsg();
    @Override
    public ReturnMsg esclientmakeacdcall(CallingMsg callingMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/createRoom");
        Gson gson=new Gson();
        String createMsg= gson.toJson(callingMsg);
        Log.e(TAG, createMsg+"Something  wrong, filesDir is null");
        requestParams.addBodyParameter("",createMsg);

/*x.http().post(requestParams, new Callback.CommonCallback<String>() {
    @Override
    public void onSuccess(String result) {
        Log.e(TAG, result.toString()+"Something  wrong, filesDir is null");
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.e(TAG, ex.toString()+"Something  wrong, filesDir is null");

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
});*/
        try {
            object= x.http().postSync(requestParams,JSONObject.class);
           // int   statusCode= object.getInt("statusCode");
            room=object.getJSONObject("room");
            roomKey=room.getString("roomKey");
          roomId=room.getString("roomId");

        }catch (Throwable throwable){
            Log.e(TAG,throwable.toString()+"121");
           Log.e(TAG,throwable.getMessage()+"121");
            Log.e(TAG,throwable.getLocalizedMessage()+"1212");
            throwable.printStackTrace();
        }

        returnMsg.setRoomKey(roomKey);
        returnMsg.setRoomId(roomId);

        return returnMsg;
    }
}
