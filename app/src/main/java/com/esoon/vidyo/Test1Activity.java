package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esoon.R;
import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.QueryMsg;
import com.esoon.pojo.RoomMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.vidyo.api.call.ESClientCancelCall;
import com.esoon.vidyo.api.call.impl.ESClientCancelCallImpl;
import com.esoon.vidyo.api.other.ESClientGetVideoConfiguration;
import com.esoon.vidyo.api.other.impl.ESClientGetVideoConfigurationImpl;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.ESClientUpdateRoom;
import com.esoon.vidyo.api.room.impl.ESClientQueryRoomImpl;
import com.esoon.vidyo.api.room.impl.ESClientUpdateRoomImpl;


import org.json.JSONArray;
import org.json.JSONObject;

public class Test1Activity extends Activity {
    private static final String TAG = "ESClientLoginImpl";
    String     msg;
    JSONObject room;
    StringBuilder   phoneMsg;
    JSONArray   jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
       Button testQuery=(Button) findViewById(R.id.testQuery);
        Log.e(TAG,"Romsg   is");
        Log.e(TAG,"Romsg   is");
        System.out.println("错误日志3");



        testQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Thread downloadRun1 = new Thread() {
                    @Override
                    public void run() {
                        down1();
                    }


                };
            // new Thread(downloadRun1).start();
                Thread downloadRun2 = new Thread() {
                    @Override
                    public void run() {
                        down2();
                    }


                };
               // new Thread(downloadRun2).start();
                Thread downloadRun3 = new Thread() {
                    @Override
                    public void run() {
                        down3();
                    }


                };
                new Thread(downloadRun3).start();
                Toast.makeText(Test1Activity.this,"phone    msg is:"+phoneMsg,Toast.LENGTH_SHORT).show();

            }
        });




    }

    private void down3() {
        ESClientCancelCall CancerCall=new ESClientCancelCallImpl();
        if(CancerCall.CancleCall(100001,"123")){
            Intent    intent=new Intent(Test1Activity.this,TestActivity.class);
            startActivity(intent);
        }


    }

    private void down2() {
        ESClientGetVideoConfiguration GetVideoConfiguration=new ESClientGetVideoConfigurationImpl();
        phoneMsg= GetVideoConfiguration.GetVideoConfiguration(this);



    }

    private void down1() {

        ScheduleInfo    scheduleInfo=new ScheduleInfo();
        ESClientUpdateRoom UpdateRoom=new ESClientUpdateRoomImpl();
        RoomMsg roomMsg=new RoomMsg(721,"会议室2","北京","关于vidyo的讨论");
        CreateRomMsg    createRomMsg=new CreateRomMsg("15002",scheduleInfo,"1001","video","default",3,roomMsg);
      if (UpdateRoom.UpdateRoom(createRomMsg)) {
          Intent    intent=new Intent(Test1Activity.this,TestActivity.class);
          startActivity(intent);

      }

    }

}
