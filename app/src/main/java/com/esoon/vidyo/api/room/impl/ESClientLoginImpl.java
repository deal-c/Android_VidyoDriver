package com.esoon.vidyo.api.room.impl;

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.esoon.vidyo.TestActivity;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.google.gson.Gson;
import com.esoon.pojo.LoginMessage;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/9.
 */

public class ESClientLoginImpl implements ESClientLoginInterface {
    boolean flag=false;
    private static final String TAG = "ESClientLoginImpl";
    JSONObject  obj;
    @Override
    public boolean LoginMessage(String userName, String userPsd) {
        RequestParams   requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/vLogin");
        LoginMessage loginMessage=new LoginMessage();
        System.out.println("userName is  :"+userName);
        System.out.println("userPsd is  :"+userPsd);
        loginMessage.setUserName(userName);
        loginMessage.setUserPsd(userPsd);
        Gson    gson=new Gson();
       String   sendMsg=gson.toJson(loginMessage);
        requestParams.addBodyParameter("",sendMsg);
        System.out.println("hello is  :"+userPsd);


       try{

          obj=  x.http().postSync(requestParams,JSONObject.class);
          int   statusCode= obj.getInt("statusCode");
           if(statusCode==0){
               flag=true;
           }
       }catch (Throwable throwable) {
           throwable.printStackTrace();
       }
        Log.e(TAG, "Something wrong, filesDir is null");
        Log.e(TAG, "Something  wrong, filesDir is null");
        Log.e(TAG,obj+ "Something  wrong, filesDir is null");
        Log.e(TAG, obj+"Something  wrong, filesDir is null");


        System.out.println("flag is  :"+flag);
return flag;

    }
}
