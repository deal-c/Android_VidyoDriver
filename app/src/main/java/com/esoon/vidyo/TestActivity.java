package com.esoon.vidyo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esoon.pojo.CallingManagerMsg;
import com.esoon.pojo.CallingMsg;
import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.QueryMsg;
import com.esoon.pojo.RoomMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.pojo.Userdata;
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.esoon.vidyo.api.call.ESClientMakeDIDCall;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;
import com.esoon.vidyo.api.call.impl.ESClientMakeDIDCallImpl;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;
import com.esoon.vidyo.api.room.impl.ESClientLoginImpl;
import com.esoon.vidyo.api.room.impl.ESClientQueryRoomImpl;
import com.esoon.vidyosample.R;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;
import com.google.gson.Gson;

public class TestActivity extends Activity {
    ScheduleInfo    scheduleInfo=new ScheduleInfo();
    private static final String TAG = "videoactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    Button  button=(Button) findViewById(R.id.ACDCALL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(downloadRun).start();

            }
            Thread downloadRun = new Thread() {
                @Override
                public void run() {
                    down();
                }


            };
        });



    }
    private void down(){
        ESClientMakeACDCall esClientMakeACDCall=new ESClientMakeACDCallImpl();
        CallingMsg callingMsg=new CallingMsg("1212","1","video");
        esClientMakeACDCall.esclientmakeacdcall(callingMsg);

    }
}
