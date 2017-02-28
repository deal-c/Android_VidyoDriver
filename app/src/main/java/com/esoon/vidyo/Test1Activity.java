package com.esoon.vidyo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esoon.pojo.QueryMsg;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.impl.ESClientQueryRoomImpl;
import com.esoon.vidyosample.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test1Activity extends Activity {
    private static final String TAG = "ESClientLoginImpl";
    String     msg;
    JSONObject room;
    JSONArray   jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
       Button testQuery=(Button) findViewById(R.id.testQuery);
        Log.e(TAG,"Romsg   is");
        Log.e(TAG,"Romsg   is");
        System.out.println("错误日志3");



        testQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              //  Toast.makeText(Test1Activity.this, queryMsg.toString()+ "123", Toast.LENGTH_LONG).show();

                Thread downloadRun1 = new Thread() {
                    @Override
                    public void run() {
                        down1();
                    }


                };
             new Thread(downloadRun1).start();
            /*  ESClientQueryRoom esClientQueryRoom = new ESClientQueryRoomImpl();
                QueryMsg queryMsg = new QueryMsg(0);
                Log.e(TAG, queryMsg + "Romsg   is");
                msg= esClientQueryRoom.esclientqueryeroom(queryMsg);*/
                Toast.makeText(Test1Activity.this, msg+ "123", Toast.LENGTH_LONG).show();


            }
        });




    }

    private void down1() {
        ESClientQueryRoom esClientQueryRoom = new ESClientQueryRoomImpl();
        QueryMsg queryMsg = new QueryMsg(0,0,"123");
        Log.e(TAG, queryMsg + "Romsg   is");
        jsonArray= esClientQueryRoom.esclientqueryeroom(queryMsg);
       msg=jsonArray.toString();

    }

}
