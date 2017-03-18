package com.esoon.vidyo.api.call.impl;

import android.graphics.ImageFormat;
import android.util.Log;

import com.esoon.vidyo.api.call.ESClientCancelCall;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.esoon.utils.VidyoUtils.NetPortalInfo;

/**
 * Created by Administrator on 2017/3/16.
 */

public class ESClientcancellcallImpl implements ESClientCancelCall {

    private static final String TAG = "ESClientcancellcallImpl";

    @Override


    public boolean cancellCall(String   roomId) {
        JSONObject object;
        RequestParams requestParams = new RequestParams(NetPortalInfo + "api/v1/video/cancelCall/"+roomId);
Log.e(TAG,roomId);
        Log.e(TAG, "cancellCall   start:");
        try {
            object = x.http().getSync(requestParams, JSONObject.class);
            int statusCode = object.getInt("statusCode");
            if (statusCode == 0) {

                Log.e(TAG, "cancellCall   success:");
                return true;
            }

        } catch (Throwable throwable) {
            Log.e(TAG, "cancell  wrong   msg is:" + throwable.toString());
            throwable.printStackTrace();
        }


        return false;
    }
}
