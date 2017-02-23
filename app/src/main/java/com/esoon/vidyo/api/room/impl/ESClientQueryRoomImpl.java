package com.esoon.vidyo.api.room.impl;

import android.util.Log;

import com.esoon.pojo.DeleteMsg;
import com.esoon.pojo.QueryMsg;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/20.
 */

public class ESClientQueryRoomImpl  implements ESClientQueryRoom {
    private static final String TAG = "ESClientLoginImpl";
    boolean flag=false;
    String  rommsg;
    @Override
    public String esclientqueryeroom(QueryMsg  queryMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/queryRoom");
        Gson gson=new Gson();
        String dMsg=gson.toJson(queryMsg);
        requestParams.addBodyParameter("",dMsg);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG,result+"1asdfasdf啊U和督查us的话爱上打撒地方23");
                rommsg=result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG,ex.toString()+"错误信息");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
 /*       Log.e(TAG,"2222222222222222"+dMsg);
        try {
           object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            if (statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        Log.e(TAG,"1asdfasdf啊U和督查us的话爱上打撒地方23"+object);
        Log.e(TAG,"1asdfasdf啊U和督查us的话爱上打撒地方23"+object);
        Log.e(TAG,"1asdfasdf啊U和督查us的话爱上打撒地方23"+object);*/
        return rommsg;
    }

}
