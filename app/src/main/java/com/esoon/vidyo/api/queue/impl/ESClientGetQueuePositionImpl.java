package com.esoon.vidyo.api.queue.impl;

import com.esoon.vidyo.api.queue.ESClientGetQueuePosition;
import com.google.gson.Gson;
import com.esoon.pojo.SearchRomMsg;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/16.
 */

public class ESClientGetQueuePositionImpl implements ESClientGetQueuePosition {
    int   roomId;
    @Override
    public int esclinetGetQueuePosition(SearchRomMsg searchRomMsg) {
        RequestParams requestParams=new RequestParams("");
        Gson gson=new Gson();
        String mysearch=gson.toJson(searchRomMsg);
        requestParams.addBodyParameter("",mysearch);
        try {
            JSONObject object= x.http().postSync(requestParams,JSONObject.class);
          roomId= object.getInt("roomId");

        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return roomId;
    }
}
