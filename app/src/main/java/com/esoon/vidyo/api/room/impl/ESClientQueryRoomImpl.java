package com.esoon.vidyo.api.room.impl;

import android.util.Log;

import com.esoon.pojo.DeleteMsg;
import com.esoon.pojo.QueryMsg;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/20.
 */

public class ESClientQueryRoomImpl  implements ESClientQueryRoom {
    private static final String TAG = "ESClientLoginImpl";
    boolean flag=false;
    JSONObject object;
    @Override
    public boolean esclientqueryeroom(QueryMsg  queryMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/queryRoom");
        Gson gson=new Gson();
        String dMsg=gson.toJson(queryMsg);
        requestParams.addBodyParameter("",dMsg);
        try {
           object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            if (statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        Log.e("123","123"+object);

        return flag;
    }

}
