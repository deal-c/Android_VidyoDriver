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
import com.esoon.vidyo.api.call.ESClientMakeACDCall;
import com.esoon.vidyo.api.call.impl.ESClientMakeACDCallImpl;
import com.esoon.vidyo.api.room.ESClientCreateRoom;
import com.esoon.vidyo.api.room.impl.ESClientCreateRoomImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class CreatemyActivity extends Activity {
    ScheduleInfo scheduleInfo = new ScheduleInfo();
    private View.OnClickListener imgViewListener;
    private Bitmap myBitmap;
    private int REQUEST_OK = 1;
    private LinearLayout ly_list;
    private static final String TAG = "CreatemyActivity";
    Button imageView;
    GridView addpic;
    private ArrayList<HashMap<String, Object>> listItem;
    BaseAdapter adapter;
    int[] checkimages = new int[20];
    String[] checktext = new String[20];
    TextView    countNum;
    int count=0;
    private String initStartDateTime = "2013年9月3日 14:44"; // 初始化开始时间
    private String initEndDateTime = "2014年8月23日 17:44"; // 初始化结束时间
    String  roomId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create);

        imageView = (Button) findViewById(R.id.addItem);
        Button createrom = (Button) findViewById(R.id.createMyRom);
        final EditText text = (EditText) findViewById(R.id.textname);
        EditText text1 = (EditText) findViewById(R.id.texttheme);
        countNum=(TextView)findViewById(R.id.countNum);
        addpic = (GridView) findViewById(R.id.addpic);

    final String    shareId =this.getSharedPreferences("shared_loginn_info", Context.MODE_PRIVATE).getString("password","get  wrong   userId");
        final EditText timeselect = (EditText) findViewById(R.id.timeseclect);
        final EditText endtime = (EditText) findViewById(R.id.end_time);

        DisplayMetrics  dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float   density=dm.density;
        int gritdviewWidth=(int)(40*checkimages.length*density);
        int itemWith=(int)(40*density);

        LinearLayout.LayoutParams   params=new LinearLayout.LayoutParams(gritdviewWidth,LinearLayout.LayoutParams.FILL_PARENT);
        addpic.setLayoutParams(params);
        addpic.setColumnWidth(itemWith);
        addpic.setHorizontalSpacing(2);
        addpic.setStretchMode(GridView.NO_STRETCH);
        addpic.setNumColumns(checkimages.length);

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 6;
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

                if(position<1){
                    viewHolder.imageView.setImageResource(R.drawable.touxiang1);
                }else   if(checktext!=null){
                    viewHolder.imageView.setImageResource(checkimages[position-1]);
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
if (checkimages==null){
    countNum.setText("人数1");
}else {
    for (int  i=0;i<checkimages.length;i++){
        if (checkimages[i]!=0){
            count++;
        }
    }
    countNum.setText("人数"+(count+1));
}

            addpic.setAdapter(adapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(roomId!=null){
                    Intent intent = new Intent(CreatemyActivity.this, SelectPicActivity.class);
                    intent.putExtra("roomId",roomId);
                    startActivity(intent);
                }else {
                    Toast.makeText(CreatemyActivity.this,"请先创建房间",Toast.LENGTH_SHORT).show();
                }

            }
        });
        timeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        CreatemyActivity.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(timeselect);
            }
        });
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        CreatemyActivity.this, initEndDateTime);
                dateTimePicKDialog.dateTimePicKDialog(endtime);
            }
        });
        final String nameString = text.getText().toString();
        final String themeStrng = text.getText().toString();
        Button back = (Button) findViewById(R.id.backTo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(CreatemyActivity.this,CallMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        createrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ESClientCreateRoom esClientCreateRoom = new ESClientCreateRoomImpl();
                CreateRomMsg createRomMsg = new CreateRomMsg(shareId, scheduleInfo, "123", "video", "default", 3, new
                        RoomMsg(nameString, themeStrng, "常州"));
                ReturnMsg    returnMsg = esClientCreateRoom.Createroom(createRomMsg);
                Toast.makeText(CreatemyActivity.this, "创建成功，key值为：" + returnMsg.getRoomKey(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CreatemyActivity.this, VideoActivity.class);
                intent.putExtra("roomkey", returnMsg.getRoomKey());
                roomId=returnMsg.getRoomId();
             //   startActivity(intent);


            }
        });

    }

    private void openAlertDialog() {
        // TODO Auto-generated method stub
        AlertDialog menuDialog = new AlertDialog.Builder(CreatemyActivity.this)
                .setTitle("邀请人员")
                .setAdapter(getAdapter(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //获取选中项的内容
                        //  addpic.setBackground(listItem.get(which).get("ItemManager"));
                    }
                }).show();


    }

    //获取设配器内容（要显示的效果）
    private ListAdapter getAdapter() {
        listItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("ImageManager", R.drawable.touxiang1);
        map1.put("ItemManager", "杰克");
        listItem.add(map1);
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("ImageManager", R.drawable.touxiang1);
        map2.put("ItemManager", "艾伦");
        listItem.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("ImageManager", R.drawable.touxiang1);
        map3.put("ItemManager", "瑞兹");
        listItem.add(map3);


        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem, R.layout.list_item,
                new String[]{"ImageManager", "ItemManager"},
                new int[]{R.id.image, R.id.text});
        return listItemAdapter;
    }

    public class ViewHolder {
        ImageView imageView;
    }
}
