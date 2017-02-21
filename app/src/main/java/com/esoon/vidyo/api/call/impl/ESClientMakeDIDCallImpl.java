package com.esoon.vidyo.api.call.impl;

import android.util.Log;

import com.esoon.pojo.CallingManagerMsg;
import com.esoon.pojo.CallingMsg;
import com.esoon.vidyo.api.call.ESClientMakeDIDCall;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/21.
 */

public class ESClientMakeDIDCallImpl implements ESClientMakeDIDCall {
    private static final String TAG = "videoactivity";
    boolean flag = false;
    JSONObject object;
    int statusCode;
    @Override
    public boolean esclientMakeDiDCall(CallingManagerMsg callingMsg) {
        RequestParams requestParams = new RequestParams("http://192.168.5.47/api/v1/video/vidyo/createRoom");
        Gson gson = new Gson();
        String calling = gson.toJson(callingMsg);
        requestParams.addBodyParameter("", calling);

        Log.e(TAG, callingMsg.toString() + "传递消息");
        try {
            object = x.http().postSync(requestParams, JSONObject.class);//使用xutils异步访问网络
            statusCode = object.getInt("statusCode");

            if (statusCode == 0) {
                flag = true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.e(TAG, object + "返回信息");//打印返回值
        Log.e(TAG, statusCode + "返回信息");//打印返回值

        return flag;
    }
}
