package com.esoon.vidyosample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.esoon.R;
import com.esoon.pojo.QueryMsg;
import com.esoon.utils.Tools;
import com.esoon.vidyo.VideoActivity;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.impl.ESCUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener {
    String userId;
    LinearLayout search_layout;
    ImageButton backToLogin;
    ImageButton callselect;
    EditText search_msg;
    JSONArray array;
    boolean myflag = true;
    ESCUtil escUtil;
    ESClientQueryRoom esClientQueryRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initdata();

    }

    private void initdata() {
        array = down6();

        if (array != null) {
            for (int j = 0; j < array.length(); j++) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = array.getJSONObject(j);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                View vtemp = this.createView(jsonObject);
                search_layout.addView(vtemp);
                myflag = false;

            }
        }
        /*

        int i;
        for (i = 0; i < 3; i++) {
            View view = this.createView(null);
            search_layout.addView(view);
        }*/
        search_layout.setOnClickListener(this);
        callselect.setOnClickListener(this);

    }

    private JSONArray down6() {

        QueryMsg queryMsg = new QueryMsg(userId, 1);
        return escUtil.eSClientQueryRoom(esClientQueryRoom, MainActivity.this, queryMsg);


    }

    private void initview() {
        backToLogin = (ImageButton) findViewById(R.id.back_login_button);
        search_layout = (LinearLayout) findViewById(R.id.search_layout);
        search_msg = (EditText) findViewById(R.id.search_msg);
        callselect = (ImageButton) findViewById(R.id.callselect);
        userId = this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("password", "get  wrong   userId");
        escUtil = new ESCUtil();
    }

    private View createView(final JSONObject o) {
        View v = LayoutInflater.from(this).inflate(R.layout.activity_inflate, null);
        TextView search_roomId = (TextView) v.findViewById(R.id.search_roomId);
        TextView themeEdTxt = (TextView) v.findViewById(R.id.themeEdTxt);
        // TextView countTime = (TextView) v.findViewById(R.id.countTime);
        TextView confTime = (TextView) v.findViewById(R.id.confTime);
        ImageButton bnt_attend = (ImageButton) v.findViewById(R.id.atendRom);
        try {
            search_layout.setTag(o.getString("roomId"));
            search_roomId.setText(o.getString("roomId"));
            themeEdTxt.setText(o.getString("roomSubject"));
            confTime.setText(o.getString("roomCreatedDate"));


            bnt_attend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                    try {
                        intent.putExtra("roomkey", o.getString("roomKey"));
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception se) {

        }

        return v;
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_login_button:
                MainActivity.this.finish();
                break;
            case R.id.callselect:
       /*  PopWindow    typePop = new PopWindow(MainActivity.this,callselect,R.layout.pop_filter);
                typePop.clickOutSideClose(true);
                typePop.showAsDropDown(callselect);*/

                AlertDialogCommon typePop = new AlertDialogCommon(MainActivity.this, R.layout.pop_filter);
                Window dialogWindow = typePop.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
                lp.x = Tools.getScreenWidth(MainActivity.this) - lp.width - 10;// dialog窗口 X坐标
            /*    lp.y = llType.getHeight()+llSearch.getHeight(); // dialog窗口 Y坐标*/
                dialogWindow.setAttributes(lp);
                typePop.showDialog();

                break;

        }


    }





}
