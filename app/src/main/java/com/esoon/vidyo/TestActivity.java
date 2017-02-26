package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.esoon.vidyo.api.room.ESClientInitialize;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;
import com.esoon.vidyo.api.room.impl.ESClientInitializeImpl;
import com.esoon.vidyo.api.room.impl.ESClientLoginImpl;
import com.esoon.vidyo.api.room.impl.ESClientQueryRoomImpl;
import com.esoon.vidyosample.R;
import com.esoon.vidyosample.VideoActivity;
import com.esoon.vidyosample.VidyoSampleApplicationkevin;
import com.google.gson.Gson;

public class TestActivity extends Activity {
    String    RoomKey;
    EditText    roomkey;
    Button  clickLogin;
    EditText    roomName;
    EditText    gettitle;
    ScheduleInfo    scheduleInfo=new ScheduleInfo();
    private static final String TAG = "videoactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcreate);
  //  Button  button=(Button) findViewById(R.id.ACDCALL);
        roomName=(EditText)findViewById(R.id.getname) ;
        gettitle=(EditText)findViewById(R.id.gettitle);
        clickLogin=(Button)findViewById(R.id.clickLogin);
        roomkey=(EditText)findViewById(R.id.Roomkey);
        this.RoomKey=this.getIntent().getStringExtra("roomKey");
        roomkey.setText(RoomKey);
        final String  nameString=roomName.getText().toString();
        final String  themeStrng=gettitle.getText().toString();
        clickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(TestActivity.this, VideoActivity.class);
                intent.putExtra("roomkey",RoomKey);
                startActivity(intent);
            }
        });
        

/*Log.e(TAG,"初始化开始");
        ESClientInitialize   esClientInitialize=new ESClientInitializeImpl();
        Log.e(TAG,"初始化开始");
        esClientInitialize.ESClientInitialize(TestActivity.this,"E:\\Android_VidyoDriver\\app\\src\\main\\res\\raw\\ca_certificates.crt");
        Log.e(TAG,"初始化开始");*/


/*
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


*/
    }
    private void down(){




    }
}
