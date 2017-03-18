package com.esoon.vidyo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.esoon.R;
import com.esoon.pojo.InviteMsg;
import com.esoon.pojo.Userdata;
import com.esoon.vidyo.api.queue.ESClientRoomInvited;
import com.esoon.vidyo.api.queue.impl.ESClientRoomInvitedImpl;
import com.esoon.vidyo.api.room.impl.ESCUtil;


public class SelectPicActivity extends Activity {
    boolean yyflag;
    private final static String TAG = "SelectPicActivity";
    private ListView lvGenerals;//listView对象
    private List<Map<String, Object>> generals;//要显示数据的集合
    private final String ImageSource = "imageSource";//map中key的值
    private final String GeneralName = "name";//map中key的值
    private BaseAdapter generalAdapter;//适配器\
    int count = 0;
    Button commit;
    ESClientRoomInvited esClientRoomInvited;
    Button cancellinvite;
    int[] checkimages = new int[20];
    String[] checktext = new String[20];
    ESCUtil escUtil;
    String roomId;
    int[] images = {
            R.drawable.select_1, R.drawable.select_2, R.drawable.select_3,
            R.drawable.select_4, R.drawable.select_5, R.drawable.select_6,
            R.drawable.select_7, R.drawable.select_7, R.drawable.select_8,
            R.drawable.select_5};
    String[] names = {};
    Userdata userdata = new Userdata();
    Userdata[] userArry = new Userdata[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seclect_pic);
        escUtil = new ESCUtil();
        initData();
        initView();
        Intent intent = this.getIntent();
        roomId = intent.getStringExtra("roomId");
        Log.e(TAG, "roomId is:" + roomId);
        final int RoomId = Integer.parseInt(roomId);
        cancellinvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectPicActivity.this, CreatemyActivity.class);
                intent.putExtra("checkimages", checkimages);
                intent.putExtra("checktext", checktext);
                //   intent.putExtra("userArry",userArry);
                if (roomId != null) {
                    int i;
                    int j=0;
                    for (i = 0; i < checktext.length; i++) {
                        if (checktext[i] != null) {
                            userdata.setUserId(checktext[i]);
                            i = i + 1;
                            userArry[j]=userdata;
                            j++;
                        }
                    }

                    InviteMsg inviteMsg = new InviteMsg(userArry, RoomId);
                    //  ESClientRoomInvited esClientRoomInvited = new ESClientRoomInvitedImpl();
            //        if (escUtil.eSClientRoomInvited(esClientRoomInvited, inviteMsg, SelectPicActivity.this, yyflag)) {
                        Toast.makeText(SelectPicActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                  //  }
                }


            }
        });

    }

    private void initData() {
// TODO Auto-generated method stub
//初始化要显示的数据集合

        generals = new ArrayList<Map<String, Object>>();
//初始化图片资源
        names = getResources().getStringArray(R.array.generals);

//初始化文字资源

//把图片和文字资源放到一个map中，然后再把该map加到generals这个list之中
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> general = new HashMap<String, Object>();
            general.put(ImageSource, images[i]);
            general.put(GeneralName, names[i]);
            generals.add(general);

        }
    }

    private void initView() {

// TODO Auto-generated method stub
        cancellinvite = (Button) findViewById(R.id.cancellinvite);
        commit = (Button) findViewById(R.id.commit);
        lvGenerals = (ListView) findViewById(R.id.lvGenerals);
        // commit=(Button)findViewById(R.id.commit);


//初始化适配器

//重点解释第一个参数是要显示的布局所在的activity对象，第二个参数是要显示数据的集合对象，第三个是指用于适配的布局页面，

//第四个是指map中的key值，第五个值是指适配布局页面和map中value对应上的布局控件
/*        generalAdapter = new SimpleAdapter(this, generals,
                R.layout.activity_select_pic,
                new String[]{ImageSource,GeneralName} , new int[]{R.id.ivTumb,R.id.tvName});*/


        generalAdapter = new BaseAdapter() {
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
            public View getView(final int position, View convertView, ViewGroup parent) {


                final ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(SelectPicActivity.this).inflate(R.layout.activity_select_pic, null);
//          这里我们使用layoutInflater对象的inflate方法将xml文件转换为一个view对象.
//          (通常传入的第二个参数为null就好)
                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivTumb);
                    viewHolder.tittle = (TextView) convertView.findViewById(R.id.tvName);
                    viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkPic);

                    // viewHolder.content = (TextView) convertView.findViewById(R.id.tv_context);
//            我们将需要用到的三个属性封装在viewHolder对象中,通过if语句先判断convertView是否为空,若为空
//            使用findViewById将三个控件分别进行绑定.
                    convertView.setTag(viewHolder);
//          建立convertView的setTag关系与viewHolder的关系

                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
//          若不为空直接取出使用,提升程序效率
                }
                viewHolder.imageView.setImageResource(images[position]);
                viewHolder.tittle.setText(names[position]);
                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            for (int i = 0; i < checktext.length; i++) {
                                if (checktext[i] == null) {
                                    checktext[i] = names[position];
                                    break;
                                }
                            }
                            for (int j = 0; j < checkimages.length; j++) {
                                if (checkimages[j] == 0) {
                                    checkimages[j] = images[position];
                                    break;
                                }
                            }
                            Log.e(TAG, "check    name    is:" + checktext[position]);

                        } else {
                            checktext[position] = null;
                            checkimages[position] = 0;
                        }
                    }
                });


                //    viewHolder.content.setText(list.get(position).item_textcontext);
                return convertView;

            }
        };
//设置适配器
        lvGenerals.setAdapter(generalAdapter);
    }

    class ViewHolder {

        ImageView imageView;
        TextView tittle;
        TextView content;
        CheckBox checkBox;

    }


}
