package com.esoon.vidyo.api.call.impl;

import android.util.Log;

import com.esoon.vidyo.api.call.ESClientCancelCall;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/3/4.
 */

public class ESClientCancelCallImpl implements ESClientCancelCall {
private    final static     String  TAG="ESClientCancelCallImpl";
boolean flag=false;
    @Override
    public boolean CancleCall(int roomId, String userId) {
        RequestParams   requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/cancelCall"+roomId);

        JSONObject  jsonObject= null;
        try {
            jsonObject = x.http().getSync(requestParams,JSONObject.class);
            int statusCode=jsonObject.getInt("statusCode");
            if (statusCode==0){
                flag=true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Log.e(TAG,"cancell  call    wrong   msg is:"+throwable.toString());
        }


Log.e(TAG,"cancell  call    success");



        return flag;
    }
}
