package com.esoon.vidyo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.esoon.R;
import com.esoon.pojo.DeleteMsg;
import com.esoon.vidyo.api.room.ESClientDeleteRoom;
import com.esoon.vidyo.api.room.impl.ESClientDeleteRoomImpl;


public class ModifyMyRomMsgActivity extends Activity {
String  roomId;
    EditText    idEdtxt;
    EditText    nameEdtxt;
    EditText    themeEdTxt;
    int myid;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_rom_msg);
        Intent  intent=getIntent();
        idEdtxt=(EditText)findViewById(R.id.idEdtxt);
        nameEdtxt=(EditText)findViewById(R.id.nameEdTxt);
        themeEdTxt=(EditText)findViewById(R.id.themeEdTxt);
        roomId= intent.getStringExtra("roomid");

      //  Toast.makeText(ModifyMyRomMsgActivity.this,"房间Id"+roomId,Toast.LENGTH_LONG).show();
        Button  deleteConfig=(Button)findViewById(R.id.deleteConfig);
        final Button  start_time=(Button)findViewById(R.id.start_time);
        final Button  end_time=(Button)findViewById(R.id.end_time);
        idEdtxt.setText(roomId);

        Button  saveRom=(Button)findViewById(R.id.savaRom);
        saveRom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEdit1()){
                    try {
                       id=Integer.parseInt(idEdtxt.getText().toString());}catch (Exception se){

                    }
                    DeleteMsg   mydeleteMsg=new DeleteMsg(id,nameEdtxt.getText().toString(),1);


                    Toast.makeText(ModifyMyRomMsgActivity.this,"正在保存,房间信息是"+mydeleteMsg,Toast.LENGTH_LONG).show();}

            }
        });
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ModifyMyRomMsgActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        start_time.setText("您选择的日期是："+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2000,1,2).show();
            }
        });
        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ModifyMyRomMsgActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        end_time.setText("您选择的日期是："+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },2000,1,2).show();
            }
        });
        deleteConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESClientDeleteRoom  esClientDeleteRoom=new ESClientDeleteRoomImpl();
               // Integer myId=Integer.parseInt(roomId);




                if (checkEdit()){

                   try {
                       myid=Integer.parseInt(idEdtxt.getText().toString());
                   }catch (Exception se){

                   }

                    DeleteMsg   deleteMsg=new DeleteMsg(myid,nameEdtxt.getText().toString(),1);
                if (esClientDeleteRoom.esClientDeleteRoom(deleteMsg)){
                    Toast.makeText(ModifyMyRomMsgActivity.this,"删除成功,房间id是:"+idEdtxt.getText().toString(),Toast.LENGTH_LONG).show();
                    Intent  intent=new Intent(ModifyMyRomMsgActivity.this, CallMainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(ModifyMyRomMsgActivity.this,"删除失败，请联系管理员",Toast.LENGTH_LONG).show();
                }}
            }
        });




    }
    private boolean checkEdit() {
        if (idEdtxt.getText().toString().trim().equals("")) {
            Toast.makeText(ModifyMyRomMsgActivity.this, "请输入房间Id", Toast.LENGTH_SHORT).show();
        } /*else if (nameEdtxt.getText().toString().trim().equals("")) {
            Toast.makeText(ModifyMyRomMsgActivity.this, "请输入完整id", Toast.LENGTH_SHORT).show();
        } else if (themeEdTxt.getText().toString().trim().equals(""))
       {
           Toast.makeText(ModifyMyRomMsgActivity.this, "请输入完整id", Toast.LENGTH_SHORT).show();
        }*/else{
            return true;
        }
        return false;
    }
    private boolean checkEdit1() {
        if (idEdtxt.getText().toString().trim().equals("")&&nameEdtxt.getText().toString().trim().equals("")&&themeEdTxt.getText().toString().trim().equals("")) {
            Toast.makeText(ModifyMyRomMsgActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }
    public void onClick(View view) {

        finish();
    }
}
