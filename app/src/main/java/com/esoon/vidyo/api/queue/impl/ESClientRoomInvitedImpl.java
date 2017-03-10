package com.esoon.vidyo.api.queue.impl;

import android.util.Log;

import com.esoon.pojo.InviteMsg;
import com.esoon.utils.VidyoUtils;
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
    private static final String TAG="ESClientRoomInvitedImpl";

    @Override
    public boolean esclientRoomInvited(InviteMsg    inviteMsg) {
        Log.e(TAG,"Invite sending");
        RequestParams requestParams=new RequestParams(VidyoUtils.queueinfo+"api/v1/video/vidyo/sendInvitedLink");
        Gson gson=new Gson();
        Log.e(TAG,"Invite roomId  is"+inviteMsg.getRoomId());
        Log.e(TAG,"Invite roomId  is"+inviteMsg.toString());
        String RoomInvite= gson.toJson(inviteMsg);
        requestParams.addBodyParameter("",RoomInvite);
        JSONObject object;
        try {
            object= x.http().postSync(requestParams,JSONObject.class);
            int   statusCode= object.getInt("statusCode");
            Log.e(TAG,"Invite back msg is"+object);
            if(statusCode==0){
                flag=true;
            }
        }catch (Throwable throwable){

            Log.e(TAG,"Invite wrong msg is"+throwable.toString());
            throwable.printStackTrace();
        }


        return flag;
    }
}
