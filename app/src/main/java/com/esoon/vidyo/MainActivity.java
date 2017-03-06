package com.esoon.vidyo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.esoon.R;
import com.esoon.pojo.CallingMsg;
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;


public class MainActivity extends Activity {
    private static final String TAG = "videoactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }

    public void onClick(View view) {
        ESClientMakeACDCall esClientMakeACDCall=new ESClientMakeACDCallImpl();
        CallingMsg callingMsg=new CallingMsg("1212",1,"video");
        esClientMakeACDCall.esclientmakeacdcall(callingMsg);


    }
}
