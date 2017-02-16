package com.esoon.vidyo.api.call.impl;

import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.google.gson.Gson;
import com.esoon.pojo.CallingMsg;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/15.
 */

public class ESClientMakeACDCallImpl    implements ESClientMakeACDCall {

boolean flag=false;
    @Override
    public boolean esclientmakeacdcall(CallingMsg callingMsg) {
        RequestParams requestParams=new RequestParams("");
        Gson gson=new Gson();
       String calling=gson.toJson(callingMsg);
        requestParams.addBodyParameter("",calling);
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
