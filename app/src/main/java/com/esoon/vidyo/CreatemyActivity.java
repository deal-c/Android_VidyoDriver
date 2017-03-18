package com.esoon.vidyo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.esoon.R;
import com.esoon.pojo.CallingMsg;
import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.ReturnMsg;
import com.esoon.pojo.ScheduleInfo;
import com.esoon.pojo.RoomMsg;
import com.esoon.pojo.Userdata;
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.impl.ESCUtil;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class CreatemyActivity extends Activity implements View.OnClickListener, ESClientCreateRoom {

    private View.OnClickListener imgViewListener;
    private Bitmap myBitmap;
    private int REQUEST_OK = 1;
    private LinearLayout ly_list;
    private static final String TAG = "CreatemyActivity";
    Button imageView;
    String nameString;
    String themeStrng;
    GridView addpic;
    private ArrayList<HashMap<String, Object>> listItem;
    BaseAdapter adapter;
    int[] checkimages = new int[20];
    String[] checktext = new String[20];
    TextView countNum;
    Button createrom;
    int count = 0;
    private String initStartDateTime = "2013年9月3日 14:44"; // 初始化开始时间
    private String initEndDateTime = "2014年8月23日 17:44"; // 初始化结束时间
    String roomId;
    String shareId;
    EditText timeselect;
    EditText endtime;
    EditText textname;
    EditText texttheme;
    Button back;
    Userdata userdata = new Userdata("10001");
    Userdata[] userArry = {userdata, userdata};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create);
        initview();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gritdviewWidth = (int) (40 * checkimages.length * density);
        int itemWith = (int) (40 * density);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gritdviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        addpic.setLayoutParams(params);
        addpic.setColumnWidth(itemWith);
        addpic.setHorizontalSpacing(2);
        addpic.setStretchMode(GridView.NO_STRETCH);
        addpic.setNumColumns(checkimages.length);

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final CreatemyActivity.ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(CreatemyActivity.this).inflate(R.layout.activity_invite, null);

                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.invitePic);


                    convertView.setTag(viewHolder);


                } else {


                    viewHolder = (CreatemyActivity.ViewHolder) convertView.getTag();
//          若不为空直接取出使用,提升程序效率
                }

                if (position < 1) {
                    viewHolder.imageView.setImageResource(R.drawable.touxiang1);
                } else if (checktext != null) {
                    viewHolder.imageView.setImageResource(checkimages[position - 1]);
                }
                viewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        return false;
                    }
                });

                return convertView;
            }
        };

        Intent intent = this.getIntent();

        checktext = intent.getStringArrayExtra("checktext");
        checkimages = intent.getIntArrayExtra("checkimages");
        if (checkimages == null) {
            countNum.setText("人数1");
        } else {
            for (int i = 0; i < checkimages.length; i++) {
                if (checkimages[i] != 0) {
                    count++;
                }
            }
            countNum.setText("人数" + (count + 1));
        }

        addpic.setAdapter(adapter);
        imageView.setOnClickListener(this);
        timeselect.setOnClickListener(this);
        endtime.setOnClickListener(this);

        back.setOnClickListener(this);
        createrom.setOnClickListener(this);

    }

    private boolean checkEdit() {
        if (textname.getText().toString().trim().equals("")) {
            Toast.makeText(CreatemyActivity.this, "房间名不能为空", Toast.LENGTH_SHORT).show();
        } else if (texttheme.getText().toString().trim().equals("")) {
            Toast.makeText(CreatemyActivity.this, "会议主题不能为空", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }


    private void initview() {
        imageView = (Button) findViewById(R.id.addItem);
        createrom = (Button) findViewById(R.id.createMyRom);
        textname = (EditText) findViewById(R.id.textname);
        texttheme = (EditText) findViewById(R.id.texttheme);
        countNum = (TextView) findViewById(R.id.countNum);
        addpic = (GridView) findViewById(R.id.addpic);
        shareId = this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("password", "get  wrong   userId");
        timeselect = (EditText) findViewById(R.id.timeseclect);
        endtime = (EditText) findViewById(R.id.end_time);
        back = (Button) findViewById(R.id.backTo);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.createMyRom:
                if (checkEdit()) {
                    nameString = textname.getText().toString();
                    themeStrng = texttheme.getText().toString();
                    boolean schedule = true;
                    Log.e(TAG, "wrong  edittext is:" + nameString);
                    ESCUtil escUtil = new ESCUtil();
                    Userdata[]  users=new Userdata[]{};
                    Userdata    userData=new Userdata();
                    ScheduleInfo scheduleInfo = new ScheduleInfo(timeselect.getText().toString(),userData,users);
                    CreateRomMsg createRomMsg = new CreateRomMsg(shareId, new RoomMsg(nameString, themeStrng, "常州"), 3, "video", "default", null, scheduleInfo, schedule, null);
                    ReturnMsg returnMsg = escUtil.eSClientCreateRoom(this, CreatemyActivity.this, createRomMsg);
                    //ReturnMsg returnMsg = esClientCreateRoom.createRoom(createRomMsg);
                    Toast.makeText(CreatemyActivity.this, "创建成功", Toast.LENGTH_LONG).show();
                    roomId = returnMsg.getRoomId();
                }
                break;

            case R.id.timeseclect:
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        CreatemyActivity.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(timeselect);

                break;
            case R.id.end_time:

                DateTimePickDialogUtil endDateTimePicKDialog = new DateTimePickDialogUtil(
                        CreatemyActivity.this, initEndDateTime);
                endDateTimePicKDialog.dateTimePicKDialog(endtime);
                break;
            case R.id.addItem:

                if (roomId != null) {
                    Intent createIntent = new Intent(CreatemyActivity.this, SelectPicActivity.class);
                    createIntent.putExtra("roomId", roomId);
                    startActivity(createIntent);
                } else {
                    Toast.makeText(CreatemyActivity.this, "请先创建房间", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.backTo:

                Intent finishIntent = new Intent(CreatemyActivity.this, CallMainActivity.class);
                startActivity(finishIntent);
                finish();
                break;

        }


    }

    @Override
    public ReturnMsg createRoom(CreateRomMsg createRomMsg) {
        return null;
    }

    @Override
    public void createRoomCallBack(Boolean flag) {

    }

    public class ViewHolder {
        ImageView imageView;
    }
}
