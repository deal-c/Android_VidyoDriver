package com.esoon.vidyo.api.queue.impl;

import android.util.Log;

import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.queue.ESClientGetQueuePosition;



import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/16.
 */

public class ESClientGetQueuePositionImpl implements ESClientGetQueuePosition {
    JSONObject object;
    boolean flag=false;
    private static final String TAG = "ESClientGetQueue";
JSONObject  queueinfo;
    int  queueNum;

    @Override
    public int esclientGetQueuePosition(int roomId) {

        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/queueinfo/"+roomId);
        Log.e(TAG, object+"ESClientGetQueuePositionImpl roomId is："+roomId);
        try {
           object= x.http().getSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            queueinfo=object.getJSONObject("queueinfo");
            queueNum=queueinfo.getInt("queueNum");

            if(statusCode==0){
                flag=true;
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
            Log.e(TAG, object+"ESClientGetQueuePositionImpl wrong   msg is："+throwable.toString());
        }

        return queueNum;
    }
}
