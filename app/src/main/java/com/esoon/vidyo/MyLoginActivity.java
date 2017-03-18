package com.esoon.vidyo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.esoon.ExampleUtil;
import com.esoon.R;
import com.esoon.pojo.JPushMsg;
import com.esoon.pojo.LoginMessage;
import com.esoon.utils.Tools;
import com.esoon.vidyo.api.other.ESClientInitJPush;
import com.esoon.vidyo.api.other.impl.ESClientInitJPushImpl;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.impl.ESCUtil;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;
import com.esoon.vidyo.api.room.impl.ESClientLoginImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MyLoginActivity extends Activity implements ESClientLoginInterface{

    private EditText login_username;
    private EditText login_password;
    private Button user_login_button;
    private CheckBox cb_remeber;
    private Button user_register_button;
    private CheckBox psdButton;
    boolean myflag = false;
    private EditText edit_id;
    boolean flag = true;
    static String YES = "yes";
    static String NO = "no";
    static String name, password;
    ESClientInitJPush   esClientInitJPush;
    ESClientLoginInterface loginInterface = new ESClientLoginImpl();
    private String isMemory = "";//isMemory变量用来判断SharedPreferences有没有数据，包括上面的YES和NO
    private String FILE = "shared_loginn_info";//用于保存SharedPreferences的文件
    private SharedPreferences sp = null;//声明一个SharedPreferences
    public static boolean isHttps = false;
    private static final String TAG = "MyLoginActivity";
    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;
    ESCUtil escUtil=new ESCUtil();
    String dialogMessage;
    String rid;
    Set<String> tagMsg;
    String aliasMsg;
    String      userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        escUtil=new ESCUtil();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_newmylogin);
        rid = JPushInterface.getRegistrationID(getApplicationContext());
        Log.e(TAG, "registedId is  :" + rid);
        setTag();
        setAlias();
        initWidget();
        //Jpush();



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

    private void setAlias() {
        // EditText aliasEdit = (EditText) findViewById(R.id.et_alias);
        String alias = rid;
        if (TextUtils.isEmpty(alias)) {
            Toast.makeText(MyLoginActivity.this, R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            Toast.makeText(MyLoginActivity.this, R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }


    private void setTag() {
        EditText tagEdit = (EditText) findViewById(R.id.et_tag);
        String tag = rid;

        // 检查 tag 的有效性
        if (TextUtils.isEmpty(tag)) {
            Toast.makeText(MyLoginActivity.this, R.string.error_tag_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!ExampleUtil.isValidTagAndAlias(sTagItme)) {
                Toast.makeText(MyLoginActivity.this, R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            tagSet.add(sTagItme);
        }

        //调用JPush API设置Tag
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));

    }

    private void showErrorDialog(String msg) {


        AlertDialog.Builder builder;
        AlertDialog alerterror = null;

        final AlertDialog finalAlerterror = alerterror;
        builder = new AlertDialog.Builder(this).setTitle(msg)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                finalAlerterror.dismiss();
                                // showDialog(DIALOG_JOIN_CONF);
                                finish();
                            }
                        });
        alerterror = builder.create();
        alerterror.show();

    }

    private void startMain() {

        name = login_username.getText().toString();
        password = login_password.getText().toString();
        Toast.makeText(MyLoginActivity.this, "正在登陆，请稍后...", Toast.LENGTH_LONG).show();
        ESClientCreateRoom esClientCreateRoom = new ESClientCreateRoomImpl();

    }


    public void remenber() {

            if (sp == null) {
                sp = getSharedPreferences(FILE, MODE_PRIVATE);
            }
            Editor edit = sp.edit();
            edit.putString("name",login_username.getText().toString() );
            edit.putString( "password",login_password.getText().toString());
            edit.commit();

    }

    private void initWidget() {

        login_username = (EditText) findViewById(R.id.edit_username);
        login_password = (EditText) findViewById(R.id.edit_password);
        user_login_button = (Button) findViewById(R.id.button_login);

        psdButton = (CheckBox) findViewById(R.id.psdButton);

        user_login_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                remenber();
                Log.d(TAG, "checkEdit():" + checkEdit());
                System.out.println("checkEdit():" + checkEdit());

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
                    name = login_username.getText().toString();
                    password = login_password.getText().toString();

                    if (!Tools.isNetworkConnected(MyLoginActivity.this)) {
                        dialog();
                    } else {
                        new Thread(downloadRunLogin).start();
                        new Thread(downloadRunJupsh).start();
                    }
                } else {
                    Toast.makeText(MyLoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
            }
        });


        psdButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if (psdButton.isChecked()) {
                    //设置EditText的密码为可见的
                    login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置密码为隐藏的
                    login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }

        });


        login_username.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    String username = login_username.getText().toString().trim();
                    if (username.length() < 4) {
                        Toast.makeText(MyLoginActivity.this, "用户名不能小于4个字符", Toast.LENGTH_LONG);
                    }
                }
            }

        });
        login_password.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    String password = login_password.getText().toString().trim();
                    if (password.length() < 4) {
                        Toast.makeText(MyLoginActivity.this, "密码不能小于4个字符", Toast.LENGTH_SHORT);
                    }
                }
            }

        });
    }

    private boolean Jpush() {
        if (aliasMsg != null) {
            Log.e(TAG, "InitJpush  start");
            JPushMsg jPushMsg = new JPushMsg(userId, "常州亿迅公司", 2, aliasMsg, aliasMsg, "", rid);
            escUtil.eSClientInitJPush(esClientInitJPush,MyLoginActivity.this,jPushMsg);
            ESClientInitJPush esClientInitJPush = new ESClientInitJPushImpl();
            esClientInitJPush.esclientInitJPush(jPushMsg);
        }
        return false;
    }

    private boolean login() {
        userId =this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("password","get  wrong   userId");
        ESCUtil escUtil =new ESCUtil();

        LoginMessage    loginMessage=new LoginMessage(name, userId, password);
        if (escUtil.eSClientLogin(this,MyLoginActivity.this,loginMessage)) {
            Intent intent = new Intent(MyLoginActivity.this, CallMainActivity.class);
            intent.putExtra("name", name);
            flag = false;
            startActivity(intent);
        }
        return flag;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MyLoginActivity.this);
        builder.setMessage("请检查网络连接");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MyLoginActivity.this.finish();
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
        if (login_username.getText().toString().trim().equals("")) {
            Toast.makeText(MyLoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (login_password.getText().toString().trim().equals("")) {
            Toast.makeText(MyLoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            return true;
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
