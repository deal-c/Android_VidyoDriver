package com.esoon.vidyo.api.room.impl;

import android.util.Log;

import com.esoon.pojo.DeleteMsg;
import com.esoon.pojo.QueryMsg;
import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询房间
 */

public class ESClientQueryRoomImpl  implements ESClientQueryRoom {
    private static final String TAG = "ESClientQueryRoomImpl";
    boolean flag=false;
    JSONArray rommsg;
    JSONObject  object;
    JSONObject  room;
    int   statusCode=1;
    @Override
    public JSONArray esclientQueryRoom(QueryMsg  queryMsg) {
        RequestParams requestParams=new RequestParams(VidyoUtils.queueinfo+"api/v1/video/vidyo/queryRoom");
        Gson gson=new Gson();
        String dMsg=gson.toJson(queryMsg);

        requestParams.addBodyParameter("",dMsg);


        try {
           object= x.http().postSync(requestParams,JSONObject.class);
            Log.e(TAG,"queryMsg  msg   is:"+object);
            statusCode  = object.getInt("statusCode");
            rommsg=object.getJSONArray("room");
            if (statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){

            System.out.println("query   wrong   msg is："+throwable.toString());
            Log.e(TAG,"queryMsg wrong msg   is:"+throwable.toString());
            throwable.printStackTrace();
        }


        return rommsg;
    }

}
