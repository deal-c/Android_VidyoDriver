package com.esoon.vidyo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.esoon.pojo.CallingMsg;
import com.esoon.pojo.Userdata;
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.impl.ESClientLoginImpl;
import com.esoon.vidyosample.R;
import com.google.gson.Gson;

public class TestActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "videoactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button test1=(Button)findViewById(R.id.test1);
        Button  acdcall=(Button)findViewById(R.id.ACDCALL);
        acdcall.setOnClickListener(this);
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESClientLoginInterface loginInterface=new ESClientLoginImpl();
                loginInterface.LoginMessage("123","123");
                System.out.print("11111111");
                Log.e(TAG, "Something went wrong, filesDir is null");
                Log.e(TAG, "Something went wrong, filesDir is null");
                Log.e(TAG, "Something went wrong, filesDir is null");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ACDCALL:
                ESClientMakeACDCall esClientMakeACDCall=new ESClientMakeACDCallImpl();
                Userdata    myuserdata=new Userdata("小明","南京","123");
                Gson    gson = new Gson();
               String   userdata=gson.toJson(myuserdata);
                CallingMsg  callingMsg=new CallingMsg("1212","video","1",userdata);
               if (esClientMakeACDCall.esclientmakeacdcall(callingMsg)){
                   Log.e(TAG, "客服正在响应");

               }

                break;

        }



    }
}
