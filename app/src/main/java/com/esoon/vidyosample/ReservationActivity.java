package com.esoon.vidyosample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.esoon.R;
import com.esoon.vidyo.CreatemyActivity;
import com.esoon.vidyo.SelectPicActivity;


public class ReservationActivity extends Activity {
GridView    addPersonView;
   ListView select_pic;
    BaseAdapter adapter;
    private BaseAdapter generalAdapter;
    private static final String TAG="ReservationActivity";
    int[] images = {
            R.drawable.select_1, R.drawable.select_2, R.drawable.select_3,
            R.drawable.select_4, R.drawable.select_5, R.drawable.select_6,
            R.drawable.select_7, R.drawable.select_7, R.drawable.select_8,
            R.drawable.select_5};
    String[] names = {};
    int[] checkimages = {
            R.drawable.select_1, R.drawable.select_2, R.drawable.select_3,
            R.drawable.select_4, R.drawable.select_5, R.drawable.select_6,
            R.drawable.select_7, R.drawable.select_7, R.drawable.select_8,
            R.drawable.select_5};
    String[] checktext = new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        initView();
        initdata();



    }
    public class ViewHolder {
        ImageView myimageView;
        ImageView imageView;
        TextView tittle;
        TextView content;
        CheckBox checkBox;
    }
    private void initdata() {
        addPersonView.setAdapter(adapter);
        select_pic.setAdapter(generalAdapter);

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
    private void initView() {
        names = getResources().getStringArray(R.array.generals);
        select_pic =(ListView)findViewById(R.id.select_pic);
        addPersonView=(GridView) findViewById(R.id.addPerson);
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
                final ReservationActivity.ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ReservationActivity.ViewHolder();
                    convertView = LayoutInflater.from(ReservationActivity.this).inflate(R.layout.activity_invite, null);

                    viewHolder.myimageView = (ImageView) convertView.findViewById(R.id.invitePic);


                    convertView.setTag(viewHolder);


                } else {


                    viewHolder = (ReservationActivity.ViewHolder) convertView.getTag();
//          若不为空直接取出使用,提升程序效率
                }

               /* if (position < 1) {
                    viewHolder.imageView.setImageResource(R.drawable.touxiang1);
                }else*/
                    viewHolder.myimageView.setImageResource(checkimages[position]);

                viewHolder.myimageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        return false;
                    }
                });

                return convertView;
            }
        };

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


                final ReservationActivity.ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ReservationActivity.ViewHolder();
                    convertView = LayoutInflater.from(ReservationActivity.this).inflate(R.layout.activity_select_pic, null);
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
                    viewHolder = (ReservationActivity.ViewHolder) convertView.getTag();
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




}
}
