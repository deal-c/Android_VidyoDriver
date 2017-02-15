package com.vidyo.myvidyo;

import com.google.gson.Gson;
import com.pojo.DeleteMsg;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/15.
 */

public class ESClientDeleteRoomImpl implements ESClientDeleteRoom{
    boolean flag=false;
    @Override
    public boolean esclientdeleteroom(DeleteMsg deleteMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/deleteRoom");
       Gson gson=new Gson();
     String dMsg=gson.toJson(deleteMsg);
        requestParams.addBodyParameter("",dMsg);
        try {
            JSONObject object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            if (statusCode==0){
                flag=true;
            }

        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        return flag;
    }
}
