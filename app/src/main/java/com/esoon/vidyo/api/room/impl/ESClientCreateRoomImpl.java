package com.esoon.vidyo.api.room.impl;

import android.util.Log;

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
    private static final String TAG = "ESClientLoginImpl";
    @Override
    public boolean createroom(CreateRomMsg createRomMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/createRoom");
        Gson gson=new Gson();
      String createMsg= gson.toJson(createRomMsg);
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
            int   statusCode= object.getInt("statusCode");
            if(statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        Log.e(TAG, object+"Something  wrong, filesDir is null");


        return flag;
    }
}
