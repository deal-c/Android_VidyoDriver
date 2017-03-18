package com.esoon.vidyo.api.room.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.esoon.pojo.CallingManagerMsg;
import com.esoon.pojo.CallingMsg;
import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.DeleteMsg;
import com.esoon.pojo.ESMessage;
import com.esoon.pojo.InviteMsg;
import com.esoon.pojo.JPushMsg;
import com.esoon.pojo.LoginMessage;
import com.esoon.pojo.QueryMsg;
import com.esoon.pojo.ReturnMsg;
import com.esoon.vidyo.api.call.ESClientGetVideoNumParticipants;
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.esoon.vidyo.api.call.ESClientMakeDIDCall;
import com.esoon.vidyo.api.call.impl.ESClientGetVideoNumParticipantsImpl;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;
import com.esoon.vidyo.api.call.impl.ESClientMakeDIDCallImpl;
import com.esoon.vidyo.api.other.ESClientGetNeworkTrafficStat;
import com.esoon.vidyo.api.other.ESClientHandlerCallBackMethod;
import com.esoon.vidyo.api.other.ESClientInitJPush;
import com.esoon.vidyo.api.other.ESClientMuteAudio;
import com.esoon.vidyo.api.other.ESClientSendMessage;
import com.esoon.vidyo.api.other.ESClientSwitchAudioDevice;
import com.esoon.vidyo.api.other.impl.ESClientGetNeworkTrafficStatImpl;
import com.esoon.vidyo.api.other.impl.ESClientInitJPushImpl;
import com.esoon.vidyo.api.other.impl.ESClientMuteAudioImpl;
import com.esoon.vidyo.api.other.impl.ESClientSendMessageImpl;
import com.esoon.vidyo.api.other.impl.ESClientSwitchAudioDeviceImpl;
import com.esoon.vidyo.api.queue.ESClientGetQueuePosition;
import com.esoon.vidyo.api.queue.ESClientRoomInvited;
import com.esoon.vidyo.api.queue.impl.ESClientGetQueuePositionImpl;
import com.esoon.vidyo.api.queue.impl.ESClientRoomInvitedImpl;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.ESClientDeleteRoom;
import com.esoon.vidyo.api.room.ESClientInitialize;
import com.esoon.vidyo.api.room.ESClientJoinRoom;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.ESClientUninitialize;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;


import org.json.JSONArray;

/**
 * Created by mountain on 2017/3/13.
 * 接口调用注册类
 */

public class ESCUtil {
    public static final int CALL_ENDED = 0;
    public static final int MSG_BOX = 1;
    public static final int CALL_RECEIVED = 2;
    public static final int CALL_STARTED = 3;
    public static final int SWITCH_CAMERA = 4;
    public static final int LOGIN_SUCCESSFUL = 5;
    public static final int LIBRARY_STARTED = 6;
    public static final int Event_Group_Chat = 7;
    public static final int Event_ShowBand = 10;
    public static final int Event_PartIn = 11;
    boolean loginflag;
    boolean getQueueflag;
    boolean initflag;
    boolean createRomflag;
    boolean deleteRomflag;
    boolean joinRomflag;
    int usedCamera = 1;
    private boolean doRender = false;
    private static final String TAG = "ESCUtil";

    /*注册登陆接口*/
    public boolean eSClientLogin(ESClientLoginInterface esClientLoginInterface, Context context, LoginMessage loginMessage) {
        ESClientLoginInterface myEsClientLoginInterface = new ESClientLoginImpl();
        loginflag = myEsClientLoginInterface.loginMessage(loginMessage);
        if (loginflag) {
            esClientLoginInterface.loginCallBack(loginflag);
            Log.e(TAG, "login success");
        } else {
            esClientLoginInterface.loginCallBack(!loginflag);
            Log.e(TAG, "login fail");
        }
        return loginflag;
    }

    /*注册初始化接口*/
    public boolean ESClientInitlize(ESClientInitialize esClientInitialize, Context context, Activity activity, String crtpath) {

        ESClientInitialize myEsClientInitialize = new ESClientInitializeImpl();
        initflag = myEsClientInitialize.ESClientInitialize(activity, crtpath);
        if (initflag) {
            esClientInitialize.ESCLientInitalizeCallback(initflag);
            Log.e(TAG, "init success");
        } else {
            esClientInitialize.ESCLientInitalizeCallback(!initflag);
            Log.e(TAG, "init fail");

        }
        return initflag;
    }

    /*注册视频中的Handler回调事件接口*/
    public void ESClientHandlerbackMsg(final ESClientHandlerCallBackMethod esClientHandlerCallBackMethod, final ESMessage esMessage, Activity activity) {
        final VidyoSampleApplicationkevin app = (VidyoSampleApplicationkevin) activity.getApplication();
        Handler message_handler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                switch (msg.what) {
                    case Event_ShowBand: {
                        app.DisableAutoLogin();
                        break;
                    }
                    case Event_PartIn:
                        app.StartConferenceMedia();
                        app.SetPreviewModeON(true);
                        app.SetCameraDevice(usedCamera);
                        app.DisableShareEvents();
                        startDevices();
                        if (esMessage.getEvent_PartIn()== Event_PartIn) {
                            esClientHandlerCallBackMethod.onPartyEventReceived(esMessage);
                            //人数变化回调方法
                        }
                        break;
                    case Event_Group_Chat:
                        if(esMessage.getEvent_Group_Chat()==Event_Group_Chat)
                        {
                          esClientHandlerCallBackMethod.onMessageEventReceived(esMessage);
                            //消息文本回调方法

                        }
                        stopDevices();

                        app.RenderRelease();

                        break;

                    case LIBRARY_STARTED:

                        break;

                    case CALL_STARTED:
                        Log.e(TAG, "CALL_STARTED success");

                        if (esMessage.getCALL_STARTED() == CALL_STARTED) {
                            esClientHandlerCallBackMethod.onMessageEventConferenceActive(esMessage);
                            //视频开始回调方法
                        }

                        break;

                    case CALL_ENDED:
                        if (esMessage.getCALL_ENDED() == CALL_ENDED) {
                            esClientHandlerCallBackMethod.onMessageEventConferenceEnded(esMessage);
                            //视频结束回调方法
                        }
                        break;

                    case MSG_BOX:

                        break;

                    case SWITCH_CAMERA:

                        break;

                    case LOGIN_SUCCESSFUL:
                        break;
                }
            }
        };
        app.setHandler(message_handler);
        Log.e(TAG, "message_handler success");

    }

    /*注册创建房间接口*/
    public ReturnMsg eSClientCreateRoom(ESClientCreateRoom esClientCreateRoom, Context context, CreateRomMsg createRomMsg) {
        ESClientCreateRoom myEsClientCreateRoom = new ESClientCreateRoomImpl();
        ReturnMsg backRoomMsg = myEsClientCreateRoom.createRoom(createRomMsg);
        if (backRoomMsg.getRoomKey() != null) {
            createRomflag = true;
            esClientCreateRoom.createRoomCallBack(createRomflag);
        } else {
            createRomflag = false;
            esClientCreateRoom.createRoomCallBack(createRomflag);
        }
return backRoomMsg;

    }

    /*注册删除房间接口*/
    public void eSClientDeleteRoom(ESClientDeleteRoom esClientDeleteRoom, Context context, DeleteMsg deleteMsg) {

        ESClientDeleteRoom deleteRoom = new ESClientDeleteRoomImpl();
        deleteRomflag = deleteRoom.esClientDeleteRoom(deleteMsg);
        if (deleteRomflag) {
            esClientDeleteRoom.esClientDeleteRoomCallBack(deleteRomflag);
        } else {
            esClientDeleteRoom.esClientDeleteRoomCallBack(!deleteRomflag);
        }

    }
/*注册加入房间*/
    public void esClientJoinRoom(ESClientJoinRoom esClientJoinRoom, Activity activity, Context context, String roomkey, String userName) {
        ESClientJoinRoom joinRoom = new ESClientJoinRoomImpl();
        joinRomflag = joinRoom.joinRom(activity, roomkey, userName);
        if (joinRomflag) {
            esClientJoinRoom.joinRomCallBack(joinRomflag);
        } else {
            esClientJoinRoom.joinRomCallBack(!joinRomflag);
        }


    }
/*注册查询*/
    public JSONArray eSClientQueryRoom(ESClientQueryRoom esClientQueryRoom, Context context, QueryMsg queryMsg) {
        esClientQueryRoom = new ESClientQueryRoomImpl();

        return esClientQueryRoom.esclientQueryRoom(queryMsg);


    }
/*注册结束初始化*/
    public void eSClientUninitialize(ESClientUninitialize esClientUninitialize, Context context, Activity activity) {
        esClientUninitialize = new ESClientUninitializeImpl();
        esClientUninitialize.esClientUninit(activity);
    }
/*注册得到当前视频人数*/
    public int eSClientGetVideoNumParticipants(ESClientGetVideoNumParticipants esClientGetVideoNumParticipants, Context context, Activity activity) {
        esClientGetVideoNumParticipants = new ESClientGetVideoNumParticipantsImpl();
        return esClientGetVideoNumParticipants.GetVideoNumParticipants(activity);
    }
/*注册拨打客服中心*/
    public ReturnMsg eSClientMakeACDCall(ESClientMakeACDCall esClientMakeACDCall, Context context, CallingMsg callingMsg) {
        esClientMakeACDCall = new ESClientMakeACDCallImpl();
        return esClientMakeACDCall.esclientmakeacdcall(callingMsg);
    }
/*注册拨打客户经理*/
    public ReturnMsg eSClientMakeDIDCall(ESClientMakeDIDCall esClientMakeDIDCall, Context context, CallingManagerMsg callingManagerMsg) {
        esClientMakeDIDCall = new ESClientMakeDIDCallImpl();
        return esClientMakeDIDCall.esclientMakeDiDCall(callingManagerMsg);
    }
/*注册获取网络数据*/
    public String eSClientGetNeworkTrafficStat(ESClientGetNeworkTrafficStat esClientGetNeworkTrafficStat, Context context, Activity activity) {
        esClientGetNeworkTrafficStat = new ESClientGetNeworkTrafficStatImpl();
        return esClientGetNeworkTrafficStat.esclientGetNeworkTrafficStat(activity);

    }
    /*注册发送文本*/
    public  void eSClientSendMessage(ESClientSendMessage    esClientSendMessage,Context context,Activity    activity,String userId,String   info){
            esClientSendMessage=new ESClientSendMessageImpl();
        esClientSendMessage.Sendchat(userId,info,activity);
    }
/*注册云推送初始化*/
    public void eSClientInitJPush(ESClientInitJPush esClientInitJPush, Context   context, JPushMsg  jPushMsg){
        esClientInitJPush=new ESClientInitJPushImpl();
        esClientInitJPush.esclientInitJPush(jPushMsg);

    }
    /*开启关闭话筒*/
    public boolean  eSClientMuteAudio(ESClientMuteAudio esClientMuteAudio, Context   context, Activity    activity, Boolean   flag){
        esClientMuteAudio=new ESClientMuteAudioImpl();
        esClientMuteAudio.MuteAudio(flag,activity);
        return flag;
    }
    /*注册扬声器接口*/
    public boolean  eSClientSwitchAudioDevice(ESClientSwitchAudioDevice esClientSwitchAudioDevice,Context   context,Activity    activity,Boolean    flag){
                                                                                                                                                                                   esClientSwitchAudioDevice.SwitchAudioDevice(activity,flag);

        return flag;
    }
/*注册排队位置*/
    public int eSClientGetQueuePosition(ESClientGetQueuePosition    esClientGetQueuePosition,Context context, int   roomId) {
        esClientGetQueuePosition=new ESClientGetQueuePositionImpl();

       return esClientGetQueuePosition.esclientGetQueuePosition(roomId);
    }
    public  boolean eSClientRoomInvited(ESClientRoomInvited esClientRoomInvitedInviteMsg,InviteMsg inviteMsg,Context    context,boolean flag){
        esClientRoomInvitedInviteMsg=new ESClientRoomInvitedImpl();
        flag=esClientRoomInvitedInviteMsg.esclientRoomInvited(inviteMsg);
        return flag;

    }
    void stopDevices() {
        doRender = false;
    }

    void startDevices() {
        doRender = true;
    }
}
