package com.esoon.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.esoon.R;
import com.esoon.pojo.DeleteMsg;
import com.esoon.vidyo.api.queue.ESClientGetQueuePosition;
import com.esoon.vidyo.api.queue.impl.ESClientGetQueuePositionImpl;
import com.esoon.vidyo.api.room.ESClientDeleteRoom;
import com.esoon.vidyo.api.room.impl.ESClientDeleteRoomImpl;
import com.esoon.vidyosample.CallMainActivity;

import com.esoon.vidyosample.VideoActivity;

public class CallingServerActivity extends Activity {
TextView    queueMsg;
    String  roomId;
    String  roomKey;
    String  TAG="CallingServerActivity";
    Button  delete;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_server);
        delete=(Button)findViewById(R.id.cancellCall);
        queueMsg=(TextView)findViewById(R.id.queueMsg);
        roomId=this.getIntent().getStringExtra("roomId");
        roomKey=this.getIntent().getStringExtra("roomkey");
        Log.e(TAG,"roomMsg  roomId  is:"+roomKey);
        Log.e(TAG,"roomMsg  roomId  is:"+roomKey);
        Log.e(TAG,"roomMsg  roomId  is:"+roomKey);
        Log.e(TAG,"roomMsg  roomId  is:"+roomKey);
        ESClientGetQueuePosition    esc=new ESClientGetQueuePositionImpl();

      int  queuenum=esc.esclientGetQueuePosition(Integer.parseInt(roomId));

        queueMsg.setText("当前排队数为"+queuenum);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                Log.e(TAG,"roomMsg  roomId  is:"+roomId);
                Log.e(TAG,"roomMsg  roomId  is:"+roomId);
                Log.e(TAG,"roomMsg  roomId  is:"+roomId);
                Log.e(TAG,"roomMsg  roomId  is:"+roomId);
                ESClientDeleteRoom  esClientDeleteRoom=new ESClientDeleteRoomImpl();
                DeleteMsg   deleteMsg=new DeleteMsg(Integer.parseInt(roomId),"",2);
             //   ESClientCancelCall  CancerCall=new ESClientCancelCallImpl();





                if (esClientDeleteRoom.esclientdeleteroom(deleteMsg)) {
                    Intent intent=new Intent(CallingServerActivity.this,CallMainActivity.class);
                    startActivity(intent);
               finish();
                }
                Thread downloadRun3 = new Thread() {
                    @Override
                    public void run() {
                        down3();
                    }


                };
             //   new Thread(downloadRun3).start();
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
                if (roomKey!=null&&flag){
               Intent   intent=new Intent(CallingServerActivity.this,VideoActivity.class);
                intent.putExtra("roomkey",roomKey);
                startActivity(intent);
                    flag=false;

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


    }
}
