package com.esoon.vidyo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.impl.ESClientLoginImpl;
import com.esoon.vidyosample.R;

public class TestActivity extends Activity {
    private static final String TAG = "videoactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button test1=(Button)findViewById(R.id.test1);
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
}
