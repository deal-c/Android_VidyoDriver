package com.esoon.vidyo.api.room.impl;

import android.util.Log;

import com.esoon.pojo.DeleteMsg;
import com.esoon.pojo.QueryMsg;
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
 * Created by Administrator on 2017/2/20.
 */

public class ESClientQueryRoomImpl  implements ESClientQueryRoom {
    private static final String TAG = "ESClientQueryRoomImpl";
    boolean flag=false;
    JSONArray rommsg;
    JSONObject  object;
    JSONObject  room;
    int   statusCode=1;
    @Override
    public JSONArray esclientqueryeroom(QueryMsg  queryMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/queryRoom");
        Gson gson=new Gson();
        String dMsg=gson.toJson(queryMsg);

        requestParams.addBodyParameter("",dMsg);
    /* x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG,result+"1asdfasdf啊U和督查us的话爱上打撒地方23");
                rommsg=result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG,ex.toString()+"错误信息");
               // rommsg=ex.toString();
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
            statusCode  = object.getInt("statusCode");
            rommsg=object.getJSONArray("room");
            if (statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
           // rommsg=throwable.toString();
            System.out.println("query   wrong   msg is："+throwable.toString());

            throwable.printStackTrace();
        }
        Log.e(TAG,"queryMsg is"+object);

        return rommsg;
    }

}
