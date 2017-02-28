package com.esoon.vidyo;


import android.util.Log;

import com.esoon.pojo.QueryBack;
import com.esoon.pojo.QueryBackMsg;
import com.esoon.pojo.QueryMsg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class QueryImpl implements QueryRom{
    private static final String TAG = "ESClientLoginImpl";
    boolean flag=false;
    JSONObject  object;
    JSONObject  roomObject;
    String  rommsg;
    ArrayList   arrayList;
    QueryBackMsg    queryBackMsg;
    List<Object> offList;
    @Override
    public List esclientqueryeroom(QueryMsg queryMsg) {
        RequestParams requestParams=new RequestParams("http://192.168.4.143:8090/api/v1/video/vidyo/room");
        Gson gson=new Gson();
        String dMsg=gson.toJson(queryMsg);
        requestParams.addBodyParameter("",dMsg);
   /*  x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
              QueryBackMsg   mymoments = new QueryBackMsg();
                Gson gson = new Gson();
               Type type=new    TypeToken<ArrayList<QueryBack>>(){}.getType();
                mymoments=gson.fromJson(result,type);
                if (result != null) {
                    Log.e("我来看看数据====", mymoments.toString());

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG,ex.toString()+"错误信息");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
*/

      try {
            object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            if (statusCode==0){
                flag=true;
            }
         //   roomObject=object.getJSONObject("room");


        offList = (List<Object>) object.getJSONObject("room");
            //Log.e("我来来看看数据====", result);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        return offList;
    }
}
