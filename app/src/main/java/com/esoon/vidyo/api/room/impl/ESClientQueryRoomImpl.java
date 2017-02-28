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
    private static final String TAG = "ESClientLoginImpl";
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
        System.out.println("错误日志1");
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
    Log.e(TAG,"2222222222222222"+dMsg);
        try {
           object= x.http().postSync(requestParams,JSONObject.class);
            statusCode  = object.getInt("statusCode");
            rommsg=object.getJSONArray("room");
            if (statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
           // rommsg=throwable.toString();
            System.out.println("错误日志2"+throwable.toString());
            Log.e(TAG,throwable.toString()+"121");
            Log.e(TAG,throwable.getMessage()+"121");
            Log.e(TAG,throwable.getLocalizedMessage()+"1212");
            throwable.printStackTrace();
        }
        Log.e(TAG,"1asdfasdf啊U和督查us的话爱上打撒地方23"+object);
        Log.e(TAG,"1asdfasdf啊U和督查us的话爱上打撒地方23"+object);
        Log.e(TAG,"1asdfasdf啊U和督查us的话爱上打撒地方23"+object);
        return rommsg;
    }

}
