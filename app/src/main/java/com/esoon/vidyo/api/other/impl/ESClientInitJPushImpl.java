package com.esoon.vidyo.api.other.impl;

import android.util.Log;

import com.esoon.pojo.JPushMsg;
import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.other.ESClientInitJPush;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/3/9.
 */

public class ESClientInitJPushImpl implements ESClientInitJPush {
    JSONObject  object;
    private final static String TAG="ESClientInitJPushImpl";

    @Override
    public boolean esclientInitJPush(JPushMsg jPushMsg) {
        RequestParams  requestParams=new RequestParams(VidyoUtils.queueinfo+"api/v1/video/push/initialize");
        Gson    gson=new Gson();
        String  msg=gson.toJson(jPushMsg);
        Log.e(TAG,"Init sendMsg is  :"+jPushMsg);
        requestParams.addBodyParameter("",msg);

        try {
           object=x.http().postSync(requestParams, JSONObject.class);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Log.e(TAG, "InitJPush  throwable is"+throwable.toString());
        }
        Log.e(TAG,"Init Msg is  :"+object);


        return false;
    }
}
