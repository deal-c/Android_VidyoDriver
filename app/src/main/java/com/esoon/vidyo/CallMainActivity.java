package com.esoon.vidyo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esoon.R;
import com.esoon.pojo.CallingManagerMsg;
import com.esoon.pojo.CallingMsg;
import com.esoon.pojo.QueryMsg;
import com.esoon.pojo.ReturnMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.utils.Contants;
import com.esoon.utils.Contants.NetCommand;
import com.esoon.utils.INetRequest;
import com.esoon.utils.Tools;
import com.esoon.vidyo.api.call.ESClientMakeDIDCall;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;
import com.esoon.vidyo.api.call.impl.ESClientMakeDIDCallImpl;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.impl.ESClientQueryRoomImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CallMainActivity extends Activity implements OnClickListener,
        INetRequest {
    JSONArray array;
    boolean myflag=true;
    String name;
    String roomid;
    String startdate;
    String enddate;
    String roomtopic;
    String roomOrganizer;
    private static String TAG = "callMain";
    private final int Msg_NetInvite = 1;
    private LinearLayout view_notifylist = null;
    ReturnMsg   returnMsg;
    String  userId;
    ScheduleInfo scheduleInfo = new ScheduleInfo();
    // 邀请进入房间的提示窗口.
    Dialog conferenceDialog = null;
    boolean isStopLoop = false;

    /**
     * 启动循环获取邀请消息
     */
    public void LoopInviteMessage() {
        if (isStopLoop) return;
        Message msg = new Message();
        msg.what = Msg_NetInvite;
        hd.sendMessageDelayed(msg, 5000);

    }


    /**
     * 显示邀请窗口
     *
     * @param oval
     */
    public void showInviteDialog(JSONObject oval) {

        conferenceDialog = Tools.createInviteDialog(this, this, oval);
        conferenceDialog.show();

        conferenceDialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        array=new JSONArray();
        Thread downloadRun1 = new Thread() {
            @Override
            public void run() {
                array=down6();
            }
        };
      //  new Thread(downloadRun1).start();
        userId =this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("password","get  wrong   userId");
   String     userName =this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("userName","get  wrong   userId");

        array=down6();
        setContentView(R.layout.activity_call_main);
        this.name = this.getIntent().getStringExtra("name");
        Button bntservice = (Button) this.findViewById(R.id.bnt_callservice);
        Button bntmanager = (Button) this.findViewById(R.id.bnt_callmanager);
        Button createRom = (Button) this.findViewById(R.id.createRom);
        TextView tx_nihao = (TextView) this.findViewById(R.id.tx_nihao);
        tx_nihao.setText(name + ",您好");
        createRom.setOnClickListener(this);
        bntservice.setOnClickListener(this);
        bntmanager.setOnClickListener(this);

        Button btngal = (Button) findViewById(R.id.btngal);
        btngal.setOnClickListener(this);
        view_notifylist = (LinearLayout) this.findViewById(R.id.view_notifylist);

        String  userId=this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("password","get  wrong   userId");

        Log.e(TAG,"getshare id  is:"+userId);


        for (int j= 0;j<array.length();j++){
                JSONObject jsonObject= null;
                try {
                    jsonObject = array.getJSONObject(j);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                View vtemp = this.createRowView(jsonObject);
                view_notifylist.addView(vtemp);
                myflag=false;
                // bnt_attend.setTag(o.getString("roomid"));
            }

        //请求通知会议列表.
 /*     HashMap mp = new HashMap();
        mp.put("operation", "notifyVidyoRoom.action");
        mp.put("userid", Contants.serveruser);
       Tools.NetGetData(Contants.serverurl, mp, CallMainActivity.this,CallMainActivity.this, NetCommand.Notifylist.getValue());*/


    }

    /**
     * 创建一行的现实view
     *
     * @param o
     */
    private View createRowView(final JSONObject o) {
        View v = LayoutInflater.from(this).inflate(
                R.layout.callrow_include, null);

        TextView text_opentime = (TextView) v.findViewById(R.id.text_opentime);
        TextView text_organizerrow = (TextView) v.findViewById(R.id.text_organizerrow);
        TextView text_topicrow = (TextView) v.findViewById(R.id.text_topicrow);
        ImageButton bnt_attend = (ImageButton) v.findViewById(R.id.bnt_attend);
       // bnt_attend.setOnClickListener(this);

        try {
                view_notifylist.setTag(o.getString("roomId"));
                text_opentime.setText(o.getString("roomId"));
                text_topicrow.setText(o.getString("roomSubject"));
                text_organizerrow.setText(o.getString("roomCreatedDate"));
          //      bnt_attend.setTag(o.getString("roomKey"));
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent=new Intent(CallMainActivity.this,TestActivity.class);
                        try {
                            intent.putExtra("roomKey",o.getString("roomKey"));
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            bnt_attend.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent=new Intent(CallMainActivity.this,VideoActivity.class);
                    try {
                        intent.putExtra("roomkey",o.getString("roomKey"));
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            /*
			bnt_attend.setTag(o.getString("roomid"));
			text_opentime.setText(o.getString("startdate") +"-" + o.getString("enddate"));
			text_topicrow.setText(o.getString("roomtopic"));
			text_organizerrow.setText("组织者:"+o.getString("roomOrganizer"));*/

        } catch (Exception se) {

        }

        return v;

    }

    private JSONArray down6() {


        ESClientQueryRoom esClientQueryRoom = new ESClientQueryRoomImpl();
        QueryMsg queryMsg = new QueryMsg(userId,1);
        Log.e(TAG,  "Romsg   is"+queryMsg);

        Log.e(TAG,  "Romsg   is"+array.toString());

        return esClientQueryRoom.esclientQueryRoom(queryMsg);


    }


    Handler hd = new Handler() {
        public void handleMessage(Message msg) {
            Bundle b = msg.getData();
            switch (msg.what) {
                case Msg_NetInvite: {
                    HashMap mp = new HashMap();
                    mp.put("operation", "inviteVidyoRoom.action");
                    mp.put("userid", Contants.serveruser);
                    Tools.NetGetData(Contants.serverurl, mp, CallMainActivity.this,
                            CallMainActivity.this, NetCommand.InviteRoom.getValue());
                }

                break;
            }

        }
    };

    @Override
    public void onWindowFocusChanged(final boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            isStopLoop = false;
            LoopInviteMessage();
        } else {
            isStopLoop = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ImageButton img = null;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.call_main, menu);
        return true;
    }

    private Dialog dialogselectalltype = null;
    private String calltype = "";

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.bnt_selectvideoroom: {
                System.out.println("bnt_selectvideoroom click ...");
                dialogselectalltype.dismiss();
                if (calltype.equals("service")) {
                    //客服中心
                    Tools.showProgressDialog(this, "加载中...");
                    ESClientMakeACDCallImpl esClientMakeACDCall=new ESClientMakeACDCallImpl();
                    CallingMsg  createRomMsg=new CallingMsg(userId,1,"video");
                    returnMsg=esClientMakeACDCall.esclientmakeacdcall(createRomMsg);
                    Log.e(TAG,"acdcall    fail");
                  if (returnMsg!=null) {
                      Intent  intent=new Intent(CallMainActivity.this, VideoActivity.class);
                    intent.putExtra("roomkey", returnMsg.getRoomKey());
                    intent.putExtra("roomId",  returnMsg.getRoomId());
                      Log.e(TAG,"acdcall    start");
                    startActivity(intent);
                    Tools.dismissProgressDialog();}
              /*      Tools.NetGetData(Contants.serverurl,
                            mp, this, this, NetCommand.CreateRoomService.getValue());*/

                } else {
                    // 经理
                  //  Tools.showProgressDialog(this, "加载中...");
                    HashMap mp = new HashMap();
                    // operation=&userid=cust2

                    ESClientMakeDIDCall EsClientMakeDIDCall = new ESClientMakeDIDCallImpl();
                    CallingManagerMsg callingMsg = new CallingManagerMsg(2,"video","vip2",userId);
                    String roomkey = EsClientMakeDIDCall.esclientMakeDiDCall(callingMsg);
                    //Log.e(TAG, roomkey + "roomkey	is:");
                    if(roomkey!=null){
                        Intent intent1 = new Intent(CallMainActivity.this, VideoActivity.class);
                        intent1.putExtra("roomkey", roomkey);
                        startActivity(intent1);
                       // Tools.dismissProgressDialog();
                    }
                   /* Tools.NetGetData(Contants.serverurl,
                            mp, this, this, NetCommand.CreateRoomManager.getValue());*/
                }
                break;
            }
            case R.id.btngal:
                Intent intent = new Intent(CallMainActivity.this, MyLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bnt_attend: {
                //参加会议按钮点击处理
                String roomid = v.getTag().toString();
                Intent it = new Intent(this, VideoActivity.class);
                it.putExtra("roomkey", roomid);
                this.startActivity(it);

                sendErrorMsg("房间号:" + roomid + "不存在", roomid);

                break;
            }

            case R.id.bnt_callservice:

                Thread downloadRun = new Thread() {
                    @Override
                    public void run() {
                        down();
                    }


                };


            {


                dialogselectalltype = Tools.createSelectCallType(this, this);
                dialogselectalltype.show();
                dialogselectalltype.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                calltype = "service";



            }
            break;

		/*	case R.id.deleteRom:
				Intent	intent1=new Intent(CallMainActivity.this,ModifyMyRomMsgActivity.class);
				startActivity(intent1);
				break;*/
            case R.id.bnt_callmanager:
                /*Thread downloadRun1 = new Thread() {
                    @Override
                    public void run() {
                        down1();
                    }


                };*/
            {
			dialogselectalltype=Tools.createSelectCallType(this, this);
			dialogselectalltype.show();

			dialogselectalltype.getWindow().setLayout(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
                calltype = "manager";

            }
            break;
            // 邀请窗口, 拒绝, 接受.
            case R.id.bnt_jujue: {
                Log.i(TAG, "拒绝点击");

                conferenceDialog.dismiss();
                LoopInviteMessage();
                break;
            }
            case R.id.bnt_jieshou: {
                Log.i(TAG, "接受点击");

                conferenceDialog.dismiss();
                //LoopInviteMessage();
                String roomid = v.getTag().toString();
                Intent it = new Intent(this, VideoActivity.class);
                it.putExtra("roomid", roomid);
                this.startActivity(it);

                sendErrorMsg("房间号:" + roomid + "不存在", roomid);

                break;
            }
            case R.id.createRom:

            {


                Intent intent4 = new Intent(CallMainActivity.this, CreatemyActivity.class);
                //	intent4.putExtra("roomKey",roomkey);
                startActivity(intent4);

                //new Thread(downloadRun3).start();


            }
            break;
            case R.id.view_notifylist:

                String roomid = v.getTag().toString();
                Intent it = new Intent(this, ModifyMyRomMsgActivity.class);
                it.putExtra("roomid", roomid);
                this.startActivity(it);
                break;


        }


    }



    private void down() {
        Intent intent = new Intent(CallMainActivity.this, VideoActivity.class);
        intent.putExtra("calltype", calltype);
        startActivity(intent);


    }


    @Override
    public void NetExecutePost(JSONObject result, int resultcode, int commandid) {
        Log.i(TAG, "resultcode =" + resultcode + " commandid=" + commandid);

        //================================================================================
        //创建房间
        if (commandid == NetCommand.CreateRoomManager.getValue()
                || commandid == NetCommand.CreateRoomService.getValue()) {
            try {
                //	Tools.showProgressDialog(this, "加载中...");
                Tools.dismissProgressDialog();
                if (resultcode == 1) {
                    int retcode = result.getInt("RetCode");
                    if (retcode == 0) {

                        String roomid = result.getString("roomid");

                        Intent it = new Intent(this, VideoActivity.class);
                        it.putExtra("roomid", roomid);
                        it.putExtra("showqueue", true);
                        it.putExtra("needdelete", true);
                        if (commandid == NetCommand.CreateRoomManager.getValue()) {
                            it.putExtra("calltype", "manager");

                        }
                        if (commandid == NetCommand.CreateRoomService.getValue()) {
                            it.putExtra("calltype", "service");

                        }
                        this.startActivity(it);

                        sendErrorMsg("房间号:" + roomid + "不存在", roomid);


                    } else {
                        // 创建房间失败, 显示失败原因
                        String ErrorMsg = result.getString("ErrorMsg");
                        //	Toast.makeText(this,ErrorMsg, 3).show();
                    }
                }
            } catch (Exception se) {
            }
        }
        // 通知会议列表.
        if (array!=null) {
            try {



                JSONArray jsonArray=new JSONArray();
                jsonArray=array;

                if (jsonArray!=null&&myflag){
                    for (int j= 0;j<jsonArray.length();j++){
                        JSONObject jsonObject=jsonArray.getJSONObject(j);
                        View vtemp = this.createRowView(jsonObject);
                        view_notifylist.addView(vtemp);
                        myflag=false;
                        // bnt_attend.setTag(o.getString("roomid"));
                    }}


                //模拟数据.
                /**
                 *
                 * bnt_attend.setTag(o.getString("roomid"));
                 text_opentime.setText(o.getString("startdate") +"-" + o.getString("enddate"));
                 text_topicrow.setText(o.getString("roomtopic"));
                 text_organizerrow.setText("组织者:"+o.getString("roomOrganizer"));
                 */

                ///===============test ==============
//				JSONObject ob = new JSONObject();
//				ob.put("roomid", "73");
//				ob.put("startdate", "2016");
//				ob.put("enddate", "2016");
//				ob.put("roomtopic", "测试模拟的房间");
//				ob.put("roomOrganizer", "陈宝吉");
//				View vtemp  = this.createRowView(ob);
//				view_notifylist.addView(vtemp);


            } catch (Exception se) {

            }


        }
        //================================================================================
        // TODO Auto-generated method stub
        if (commandid == NetCommand.InviteRoom.getValue()) {
            // 收到"邀请进入房间" 数据包
            if (conferenceDialog != null && conferenceDialog.isShowing()) {
                // 已经有弹出提示了. 忽略邀请信息.
                //LoopInviteMessage();
                Log.i(TAG, "正在显示对话窗口...");
                return;
            }

            if (resultcode == 1) {
                // 获取数据包.
                try {

                    Object oval = result.get("conference");
                    //数据 为空的时候 格式:{conference:""}

                    if (oval instanceof JSONObject) {
                        this.showInviteDialog((JSONObject) oval);

                    } else {
                        // 没拿到数据.
                        LoopInviteMessage();
                    }

                } catch (Exception se) {
                    se.printStackTrace();
                }

            } else {
                // 没拿到数据.
                LoopInviteMessage();
            }

        }

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.menu_test_enterroom: {
                    JSONObject ob = new JSONObject();
                    ob.put("roomid", "73");
                    ob.put("startdate", "2016");
                    ob.put("enddate", "2016");
                    ob.put("roomtopic", "测试模拟的房间");
                    ob.put("roomOrganizer", "陈宝吉");
                    View vtemp = this.createRowView(ob);
                    view_notifylist.addView(vtemp);

                    break;
                }

            }
        } catch (Exception se) {
        }
        return true;

    }

    /**
     * 发送错误信息.
     *
     * @param msg
     * @param roomid
     */
    public void sendErrorMsg(String msg, String roomid) {
        HashMap<String, Object> mp = new HashMap<String, Object>();
        mp.put("operation", "sendErrorMsg.action");
        mp.put("userid", Contants.serveruser);
        mp.put("roomid", roomid);
        mp.put("errorCode", "10001");
        mp.put("errorMsg", msg);
        mp.put("errorAbstract", msg);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mp.put("occurTime", df.format(new Date()));

        mp.put("phoneModel", android.os.Build.MODEL);
        mp.put("vidyoVersion", "3.3");

        Tools.NetGetData(Contants.serverurl,
                mp, this, this, NetCommand.ErrorMsg.getValue());


    }

    private View createmyRowView(JSONObject o) {
        View v = LayoutInflater.from(this).inflate(
                R.layout.create, null);
        Button createRom = (Button) findViewById(R.id.createRom);
        createRom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CallMainActivity.this, CallingActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }


}
