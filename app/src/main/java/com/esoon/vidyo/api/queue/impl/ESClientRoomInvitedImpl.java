package com.esoon.vidyo.api.queue.impl;

import com.esoon.vidyo.api.queue.ESClientRoomInvited;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/16.
 */

public class ESClientRoomInvitedImpl implements ESClientRoomInvited{
    boolean flag=false;
    @Override
    public boolean esclientRoomInvited(int roomId) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/sendInvitedLink");
        Gson gson=new Gson();
        String RoomId= gson.toJson(roomId);
        requestParams.addBodyParameter("",RoomId);
        JSONObject object;
        try {
            object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            if(statusCode==0){
                flag=true;
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }


        return flag;
    }
}
