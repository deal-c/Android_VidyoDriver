package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.esoon.pojo.CallingMsg;
import com.esoon.pojo.DeleteMsg;
import com.esoon.pojo.ReturnMsg;
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;
import com.esoon.vidyo.api.queue.ESClientGetQueuePosition;
import com.esoon.vidyo.api.queue.impl.ESClientGetQueuePositionImpl;
import com.esoon.vidyo.api.room.ESClientDeleteRoom;
import com.esoon.vidyo.api.room.impl.ESClientDeleteRoomImpl;
import com.esoon.vidyosample.CallMainActivity;
import com.esoon.vidyosample.R;
import com.esoon.vidyosample.VideoActivity;

public class CallingServerActivity extends Activity {
TextView    queueMsg;
    String  roomId;
    String  roomKey;
    String  TAG="CallingServerActivity";
    Button  delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_server);
        delete=(Button)findViewById(R.id.cancellCall);
        queueMsg=(TextView)findViewById(R.id.queueMsg);
        roomId=this.getIntent().getStringExtra("roomId");
        roomKey=this.getIntent().getStringExtra("roomkey");
        ESClientGetQueuePosition    esc=new ESClientGetQueuePositionImpl();
      int  queuenum=esc.esclientGetQueuePosition(roomId);

        queueMsg.setText("当前排队数为"+queuenum);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread downloadRun3 = new Thread() {
                    @Override
                    public void run() {
                        down3();
                    }


                };
                new Thread(downloadRun3).start();
            }
        });

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);//休眠3秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (roomKey!=null){
               Intent   intent=new Intent(CallingServerActivity.this,VideoActivity.class);
                intent.putExtra("roomkey",roomKey);
                startActivity(intent);

                }

            }
        }.start();

     /*  ESClientMakeACDCall esClientMakeACDCall=new ESClientMakeACDCallImpl();
        CallingMsg callingMsg=new CallingMsg("1","video");
        ReturnMsg roomkey=esClientMakeACDCall.esclientmakeacdcall(callingMsg);
        Intent intent1=new Intent(CallingServerActivity.this,VideoActivity.class);
        intent1.putExtra("roomkey",roomkey.getRoomKey());*/
/*
        ESClientGetQueuePosition    esClientGetQueuePosition=new ESClientGetQueuePositionImpl();
       esClientGetQueuePosition.esclientGetQueuePosition(roomId);*/

   /*     Log.e(TAG,"RoomKey  is："+roomKey);
        Intent intent1=new Intent(CallingServerActivity.this,VideoActivity.class);
        intent1.putExtra("roomkey",roomKey);






       startActivity(intent1);*/




    }

    private void down3() {
       ESClientDeleteRoom  esClientDeleteRoom=new ESClientDeleteRoomImpl();
        DeleteMsg   deleteMsg=new DeleteMsg(Integer.parseInt(roomId),"",2);
     if ( esClientDeleteRoom.esclientdeleteroom(deleteMsg)) {
         Intent intent=new Intent(CallingServerActivity.this,CallMainActivity.class);
         startActivity(intent);
this.finish();
     }

    }
}
