package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esoon.pojo.DeleteMsg;
import com.esoon.vidyo.api.room.ESClientDeleteRoom;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.impl.ESClientDeleteRoomImpl;
import com.esoon.vidyo.api.room.impl.ESClientLoginImpl;
import com.esoon.vidyosample.CallMainActivity;
import com.esoon.vidyosample.R;


public class modifyRomMsgActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_rom_msg);

        Button  deleteConfig=(Button)findViewById(R.id.deleteConfig);
        deleteConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESClientDeleteRoom  esClientDeleteRoom=new ESClientDeleteRoomImpl();
                DeleteMsg   deleteMsg=new DeleteMsg(4,"1",10001);
                if (esClientDeleteRoom.esclientdeleteroom(deleteMsg)){
                    Toast.makeText(modifyRomMsgActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                    Intent  intent=new Intent(modifyRomMsgActivity.this, CallMainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(modifyRomMsgActivity.this,"删除失败，请联系管理员",Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    public void onClick(View view) {

        finish();
    }
}
