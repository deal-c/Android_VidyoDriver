package com.esoon.vidyo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.pojo.RoomMsg;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;
import com.esoon.vidyosample.R;
import com.esoon.vidyosample.VideoActivity;

import java.io.FileNotFoundException;

public class CreatemyActivity extends Activity {
ScheduleInfo    scheduleInfo=new ScheduleInfo();
    private View.OnClickListener imgViewListener;
    private Bitmap myBitmap;
    private int REQUEST_OK = 1;
    private LinearLayout ly_list;
    private static final String TAG = "videoactivity";
    ImageView   imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        imageView =(ImageView)findViewById(R.id.add);
        Button  createrom=(Button)findViewById(R.id.createMyRom);
        EditText    text=(EditText)findViewById(R.id.textname);
        EditText    text1=(EditText)findViewById(R.id.texttheme) ;
        final Button  timeselect=(Button)findViewById(R.id.timeseclect);
        final Button  endtime=(Button)findViewById(R.id.end_time);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent intent = new Intent();
                //intent = new Intent(Intent.ACTION_GET_CONTENT);
                *//* 开启Pictures画面Type设定为image *//*
                intent.setType("image*//*");
                *//* 使用Intent.ACTION_GET_CONTENT这个Action *//*
                intent.setAction(Intent.ACTION_GET_CONTENT);
                *//* 取得相片后返回本画面 *//*
                startActivityForResult(intent, 1);*/
            }
        });
        timeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreatemyActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                       timeselect.setText("您选择的日期是："+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2000,1,2).show();
            }
        });
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreatemyActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        endtime.setText("您选择的日期是："+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2000,1,2).show();
            }
        });
        final String  nameString=text.getText().toString();
        final String  themeStrng =text.getText().toString();
Button  back=(Button)findViewById(R.id.backTo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        createrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESClientCreateRoom  esClientCreateRoom=new ESClientCreateRoomImpl();
                CreateRomMsg    createRomMsg=new CreateRomMsg("123",scheduleInfo,"123","video","default",1,new
                        RoomMsg(nameString,themeStrng,"11"));
                String  roomkey=esClientCreateRoom.createroom(createRomMsg);
                Toast.makeText(CreatemyActivity.this,"创建响应房间key值："+roomkey,Toast.LENGTH_LONG).show();
                Log.d(TAG,"33333333333333333成功了么");
                Log.d(TAG,"33333333333333333成功了么"+esClientCreateRoom.createroom(createRomMsg));
                Intent  intent=new Intent(CreatemyActivity.this, VideoActivity.class);
                    intent.putExtra("roomkey",roomkey);
                    startActivity(intent);
              /*  if (esClientCreateRoom.createroom(createRomMsg));
                {
                    Intent  intent=new Intent(CreatemyActivity.this, VideoActivity.class);
                    intent.putExtra("roomid", "4");
                    intent.putExtra("calltype","3");
                    startActivity(intent);
                }*/

            }
        });

    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode  == REQUEST_OK) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(this
                        .getContentResolver().openInputStream(Uri.parse(selectedImage.toString())));
                imageView.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "上传成功",
                        Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}
