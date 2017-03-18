package com.esoon.vidyosample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esoon.ExampleUtil;
import com.esoon.R;
import com.esoon.pojo.JPushMsg;
import com.esoon.pojo.LoginMessage;
import com.esoon.utils.Tools;
import com.esoon.vidyo.CallMainActivity;
import com.esoon.vidyo.MyLoginActivity;
import com.esoon.vidyo.api.other.ESClientInitJPush;
import com.esoon.vidyo.api.other.impl.ESClientInitJPushImpl;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.impl.ESCUtil;

import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


public class LoginActivity extends Activity implements View.OnClickListener, View.OnTouchListener, ESClientLoginInterface {
    private boolean myflag = false;
    private SharedPreferences sp = null;
    private LinearLayout idLayout;
    private LinearLayout psdLayout;
    private EditText loginUserId;
    private EditText loginUserPsd;
    private Button login_button;
    boolean flag = true;
    ESCUtil escUtil;
    private String FILE = "shared_loginn_info";
    private static final String TAG = "MyLoginActivity";
    static String name, password;
    String rid;
    String userId;
    Set<String> tagMsg;
    String aliasMsg;
    ESClientInitJPush esClientInitJPush;
    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        escUtil = new ESCUtil();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_login);
        rid = JPushInterface.getRegistrationID(getApplicationContext());
        Log.e(TAG, "registedId is  :" + rid);
        setTag();
        setAlias();
        initview();
        initdata();


    }

    private void setAlias() {
        // EditText aliasEdit = (EditText) findViewById(R.id.et_alias);
        String alias = rid;
        if (TextUtils.isEmpty(alias)) {
            Toast.makeText(LoginActivity.this, R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            Toast.makeText(LoginActivity.this, R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            aliasMsg = alias;
            Log.e(TAG, "alias msg is" + alias);
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }

            ExampleUtil.showToast(logs, getApplicationContext());
        }

    };
    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;

            Log.e(TAG, "alias msg is:" + aliasMsg);
            Log.e(TAG, "tags msg is:" + tags.toString());
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }

            ExampleUtil.showToast(logs, getApplicationContext());
        }

    };

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("请检查网络连接");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                LoginActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private boolean checkEdit() {
        if (loginUserId.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (loginUserPsd.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    private void setTag() {
        EditText tagEdit = (EditText) findViewById(R.id.et_tag);
        String tag = rid;

        // 检查 tag 的有效性
        if (TextUtils.isEmpty(tag)) {
            Toast.makeText(LoginActivity.this, R.string.error_tag_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!ExampleUtil.isValidTagAndAlias(sTagItme)) {
                Toast.makeText(LoginActivity.this, R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            tagSet.add(sTagItme);
        }

        //调用JPush API设置Tag
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));

    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;
                case MSG_SET_TAGS:
                    Log.d(TAG, "Set tags in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
                    break;

                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    private void initdata() {
        login_button.setOnClickListener(this);


    }

    private void initview() {
        login_button = (Button) findViewById(R.id.login_button);
        idLayout = (LinearLayout) findViewById(R.id.idLayout);
        psdLayout = (LinearLayout) findViewById(R.id.psdLayout);
        loginUserId = (EditText) findViewById(R.id.userId);
        loginUserPsd = (EditText) findViewById(R.id.userPsd);


    }

    public void remenber() {

        if (sp == null) {
            sp = getSharedPreferences(FILE, MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name", loginUserId.getText().toString());
        edit.putString("password", loginUserPsd.getText().toString());
        edit.commit();

    }

    private boolean login() {
        userId = this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("password", "get  wrong   userId");
        ESCUtil escUtil = new ESCUtil();

        LoginMessage loginMessage = new LoginMessage(name, userId, password);
        if (escUtil.eSClientLogin(this, LoginActivity.this, loginMessage)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("name", name);
            flag = false;
            startActivity(intent);
        }
        return flag;
    }

    private boolean Jpush() {
        if (aliasMsg != null) {
            Log.e(TAG, "InitJpush  start");
            JPushMsg jPushMsg = new JPushMsg(userId, "常州亿迅公司", 2, aliasMsg, aliasMsg, "", rid);
            escUtil.eSClientInitJPush(esClientInitJPush, LoginActivity.this, jPushMsg);
            ESClientInitJPush esClientInitJPush = new ESClientInitJPushImpl();
            esClientInitJPush.esclientInitJPush(jPushMsg);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                remenber();


                Thread downloadRunLogin = new Thread() {
                    @Override
                    public void run() {
                        myflag = login();

                    }


                };

                Thread downloadRunJupsh = new Thread() {
                    @Override
                    public void run() {
                        myflag = Jpush();

                    }


                };

                if (checkEdit()) {
                    name = loginUserId.getText().toString();
                    password = loginUserPsd.getText().toString();

                    if (!Tools.isNetworkConnected(LoginActivity.this)) {
                        dialog();
                    } else {
                        new Thread(downloadRunLogin).start();
                        new Thread(downloadRunJupsh).start();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
                break;


        }
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
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
        }
        return false;
    }

    @Override
    public boolean loginMessage(LoginMessage loginMessage) {
        return false;
    }

    @Override
    public boolean loginCallBack(boolean string) {
        return false;
    }
}