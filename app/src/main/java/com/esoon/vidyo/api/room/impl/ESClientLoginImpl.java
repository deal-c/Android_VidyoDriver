package com.esoon.vidyo.api.room.impl;

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.TestActivity;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.google.gson.Gson;
import com.esoon.pojo.LoginMessage;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 登陆
 */

public class ESClientLoginImpl implements ESClientLoginInterface {
    boolean flag = false;
    private static final String TAG = "ESClientLoginImpl";
    JSONObject obj;

    @Override
    public boolean loginMessage(LoginMessage loginMessage) {
        RequestParams requestParams = new RequestParams(VidyoUtils.NetPortalInfo + "api/v1/video/vidyo/vLogin");

        System.out.println("userName is  :" + loginMessage.getUserName());
        System.out.println("userPsd is  :" + loginMessage.getUserPsd());

        Gson gson = new Gson();
        String sendMsg = gson.toJson(loginMessage);
        requestParams.addBodyParameter("", sendMsg);

        Log.d(TAG, obj + "Login   success");

        try {

            obj = x.http().postSync(requestParams, JSONObject.class);
            int statusCode = obj.getInt("statusCode");
            if (statusCode == 0) {
                flag = true;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();

            Log.e(TAG, "Login wrong  success is" + throwable.toString());
        }

        System.out.println("flag is  :" + flag);
        return flag;

    }

    @Override
    public boolean loginCallBack(boolean flag) {

        if (flag) {
            Log.e(TAG, "login success");

        } else {
            Log.e(TAG, "login fail");
        }


        return flag;
    }


}
