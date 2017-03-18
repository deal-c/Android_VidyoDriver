package com.esoon.vidyo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.esoon.R;
import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.ESMessage;
import com.esoon.pojo.ReturnMsg;
import com.esoon.pojo.RoomMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.vidyo.api.other.ESClientHandlerCallBackMethod;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.ESClientInitialize;
import com.esoon.vidyo.api.room.impl.ESCUtil;

public class TestESCActivity extends Activity implements ESClientInitialize, ESClientHandlerCallBackMethod,ESClientCreateRoom {
    private static final String TAG = "TestESCActivity";
    ESClientInitialize esClientInitialize;
    ESCUtil escUtil = new ESCUtil();
    boolean schedule = true;
    ESMessage esMessage = new ESMessage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conference);
        initview();

        /*esMessage.setCALL_STARTED();
        esMessage.setEvent_Group_Chat();
        escUtil.handlerbackMsg(this, esMessage, TestESCActivity.this);
        escUtil.initlize(this, this, TestESCActivity.this, "E:\\Android_VidyoDriver\\app\\src\\main\\res\\raw\\ca_certificates.crt");*/


        Thread downloadRun1 = new Thread() {
            @Override
            public void run() {
                down1();
            }


        };

        new Thread(downloadRun1).start();
       // ESClientJoinRoom EsclientJoinRom = new ESClientJoinRoomImpl();
      //  EsclientJoinRom.JoinRom(this, "4iQf2PdsslTVWhlChnPqCl5FDBM", "123");

    }

    private void initview() {


    }

    private void down1() {
      /*  ScheduleInfo scheduleInfo = new ScheduleInfo("1997-10-20");
        CreateRomMsg createRomMsg = new CreateRomMsg("123", null, null, schedule, scheduleInfo, null, "default", "video", 3, new
                RoomMsg("测试", "测试", "上海"));
        escUtil.eSClientCreateRoom(this,TestESCActivity.this,createRomMsg);*/
    }

    @Override
    public boolean ESClientInitialize(Activity activity, String CRTpath) {
        return false;
    }

    @Override
    public boolean ESCLientInitalizeCallback(boolean flag) {
        if (flag) {
            Log.e(TAG, "回调确实可行");
        }

        return false;
    }


    @Override
    public void onMessageEventConferenceActive(ESMessage messages) {
        Log.e(TAG, "视频已经开始，请做处理");

    }

    @Override
    public void onMessageEventConferenceEnded(ESMessage messages) {

        Log.e(TAG, "视频已经结束");
    }

    @Override
    public void onMessageEventOnOther(ESMessage messages) {

    }

    @Override
    public void onMessageEventJoinProgress(ESMessage messages) {

    }

    @Override
    public void onMessageEventReceived(ESMessage messages) {

    }

    @Override
    public void onInviteEventReceived(ESMessage message) {

    }

    @Override
    public void onPartyEventReceived(ESMessage message) {
        Log.e(TAG, "有人加入房间");
    }

    @Override
    public ReturnMsg createRoom(CreateRomMsg createRomMsg) {
        return null;
    }

    @Override
    public void createRoomCallBack(Boolean flag) {
if(flag){
    Log.e(TAG,"创建房间成功回调");

}
    }
}
