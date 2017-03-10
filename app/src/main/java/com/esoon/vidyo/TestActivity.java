package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esoon.R;
import com.esoon.pojo.ScheduleInfo;

public class TestActivity extends Activity {
    String    RoomKey;
    EditText    roomkey;
    Button  clickLogin;
    EditText    roomName;
    EditText    gettitle;
    ScheduleInfo    scheduleInfo=new ScheduleInfo();
    private static final String TAG = "TestActivity";
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
