package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.pojo.room;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;
import com.esoon.vidyosample.R;
import com.esoon.vidyosample.VideoActivity;

public class CreateActivity extends Activity {
ScheduleInfo    scheduleInfo=new ScheduleInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Button  createrom=(Button)findViewById(R.id.createMyRom);
        EditText    text=(EditText)findViewById(R.id.textname);
        EditText    text1=(EditText)findViewById(R.id.texttheme) ;
        final String  nameString=text.getText().toString();
        final String  themeStrng =text.getText().toString();

        createrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESClientCreateRoom  esClientCreateRoom=new ESClientCreateRoomImpl();
                CreateRomMsg    createRomMsg=new CreateRomMsg("123",scheduleInfo,"123","video","default",1,new
                        room(nameString,themeStrng,"11"));


                if (esClientCreateRoom.createroom(createRomMsg));
                {
                    Intent  intent=new Intent(CreateActivity.this, VideoActivity.class);
                    intent.putExtra("roomid", "4");
                    intent.putExtra("calltype","3");
                    startActivity(intent);
                }

            }
        });

    }
}
