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

import static com.esoon.utils.VidyoUtils.NetPortalInfo;

/**
 * 拨打客服中心
 */

public class ESClientMakeACDCallImpl implements ESClientMakeACDCall {
    private static final String TAG = "ESClientMakeACDCallImpl";
    boolean flag = false;
    JSONObject object;
    JSONObject  room;
    String  roomKey;
    String  roomId;
    ReturnMsg   returnMsg=new   ReturnMsg();
    @Override
    public ReturnMsg esclientmakeacdcall(CallingMsg callingMsg) {
        RequestParams requestParams=new RequestParams(NetPortalInfo+"api/v1/video/vidyo/createRoom");
        Gson gson=new Gson();
        String createMsg= gson.toJson(callingMsg);
        Log.e(TAG,"ACDCall   msg is:"+createMsg);
        requestParams.addBodyParameter("",createMsg);

        Log.e(TAG,"ACDCall   start:");
        try {
            object= x.http().postSync(requestParams,JSONObject.class);
           // int   statusCode= object.getInt("statusCode");
            room=object.getJSONObject("room");
            roomKey=room.getString("roomKey");
          roomId=room.getString("roomId");

        }catch (Throwable throwable){
            Log.e(TAG,"ACDCall  wrong   msg is:"+throwable.toString());
            throwable.printStackTrace();
        }

        returnMsg.setRoomKey(roomKey);
        returnMsg.setRoomId(roomId);
        Log.e(TAG,"ACDCall   success:");
        Log.e(TAG,"ACDCall  backMsg   msg is:"+object);
        return returnMsg;
    }
}
