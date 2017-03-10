package com.esoon.vidyo.api.room.impl;

import android.util.Log;
import android.widget.Toast;

import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.ReturnMsg;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.esoon.utils.VidyoUtils.queueinfo;

/**
 * 创建房间
 */

public class ESClientCreateRoomImpl implements ESClientCreateRoom {
    boolean flag=false;
    JSONObject object;
    JSONObject  room;
    String  roomKey;
    String  roomId;
    ReturnMsg   returnMsg=new   ReturnMsg();
    private static final String TAG = "ESClientCreateRoomImpl";
    @Override
    public ReturnMsg Createroom(CreateRomMsg createRomMsg) {
        RequestParams requestParams=new RequestParams(queueinfo+"api/v1/video/vidyo/createRoom");
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
            roomId=room.getString("roomId");
            Log.e(TAG, "createRoom msg is:"+object);
            if(statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
            Log.e(TAG, "createRoom fail ");
            Log.e(TAG, "createRoom  throwable is"+throwable.toString());
        }


        Log.e(TAG, "createroom   success");

        returnMsg.setRoomKey(roomKey);
        returnMsg.setRoomId(roomId);
        return returnMsg;
    }
}
