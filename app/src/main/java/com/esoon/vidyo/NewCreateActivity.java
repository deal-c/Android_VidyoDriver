package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esoon.R;
import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.RoomMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;

import com.esoon.vidyosample.VideoActivity;

import cn.jpush.android.api.JPushInterface;

public class NewCreateActivity extends Activity {
    String    RoomKey;
    EditText    roomkey;
    Button  clickLogin;
    EditText    roomName;
    EditText    gettitle;
    String title;

    private static final String TAG = "NewCreateActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcreate);

        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
             title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            RoomKey = bundle.getString(JPushInterface.EXTRA_ALERT);

        }






        roomName=(EditText)findViewById(R.id.getname) ;
        gettitle=(EditText)findViewById(R.id.gettitle);
        clickLogin=(Button)findViewById(R.id.clickLogin);
        roomkey=(EditText)findViewById(R.id.Roomkey);
     //   this.RoomKey=this.getIntent().getStringExtra("roomKey");
        roomkey.setText(RoomKey);
        final String  nameString=roomName.getText().toString();
        final String  themeStrng=gettitle.getText().toString();
        clickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent=new Intent(NewCreateActivity.this, VideoActivity.class);
                intent.putExtra("roomkey",RoomKey);
                startActivity(intent);
            }
        });






    }
}
