package com.esoon.vidyo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.esoon.R;
import com.esoon.vidyo.api.other.ESClientCallBackMsg;
import com.esoon.vidyo.api.other.impl.ESClientCallBackMsgImpl;
import com.esoon.vidyo.api.room.ESClientInitialize;
import com.esoon.vidyo.api.room.impl.ESClientInitializeImpl;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/7.
 */
public class Test4Activity extends Activity {


    private Button button;
    private TextView textView;
    private ArrayList<HashMap<String, Object>> listItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activty2);
        ESClientCallBackMsg esClientCallBackMsg=new ESClientCallBackMsgImpl();
        ESClientInitialize esClientInitialize=new ESClientInitializeImpl();
        esClientCallBackMsg.esclientHandler(this);

        esClientInitialize.ESClientInitialize(this,"E:\\Android_VidyoDriver\\app\\src\\main\\res\\raw\\ca_certificates.crt");

        esClientCallBackMsg.onMessageEventConferenceActive(this);

    }
}