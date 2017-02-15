package com.vidyo.myvidyo;

import com.esoon.LmiDeviceManager.LmiDeviceManagerView;
import com.google.gson.Gson;
import com.pojo.Mymessage;
import com.pojo.RoomMsg;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/14.
 */

public class ESClientCreateRoomImpl implements ESClientCreateRoom {
    boolean flag=false;
    @Override
    public boolean createroom(Mymessage mymessage) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/createRoom");
        Gson gson=new Gson();
      String createMsg= gson.toJson(mymessage);
        requestParams.addBodyParameter("",createMsg);
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
