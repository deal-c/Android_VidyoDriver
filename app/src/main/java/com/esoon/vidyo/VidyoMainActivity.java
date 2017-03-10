package com.esoon.vidyo;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.esoon.R;
import com.esoon.pojo.QueryMsg;
import com.esoon.utils.VidyoUtils;
import com.esoon.vidyo.api.room.ESClientQueryRoom;
import com.esoon.vidyo.api.room.impl.ESClientQueryRoomImpl;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.zip.Inflater;

public class VidyoMainActivity extends Activity {
    private  final static String TAG="VidyoMainActivity";
ListView    queryView;
BaseAdapter adapter;
    JSONArray array;
    JSONObject jsonObject;
    JSONArray   newarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidyo_main);
        initView();
       Thread sonThread=new Thread(){
        @Override
        public void run() {
            newarray=queryRom();

        }};

      new Thread(sonThread).start();
        Log.e(TAG,  "VidyoMainActivity Romsg is"+newarray.toString());
        adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return newarray.length();
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

              View view= LayoutInflater.from(VidyoMainActivity.this).inflate(R.layout.callrow_include,null);
              TextView  text_opentime=(TextView)view.findViewById(R.id.text_opentime);
                TextView    text_topicrow=(TextView)view.findViewById(R.id.text_topicrow);
                TextView text_organizerrow=(TextView)view.findViewById(R.id.text_organizerrow);
                try {
                    jsonObject=array.getJSONObject(position);
                    text_opentime.setText(jsonObject.getString("roomName"));
                    text_topicrow.setText(jsonObject.getString("roomSubject"));
                    text_organizerrow.setText(jsonObject.getString("roomScheduleStartDate"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                return view;
            }
        };


    /*      Log.e(TAG,"adpter reach");
        Log.e(TAG,  "VidyoMainActivity Romsg is"+newarray.toString());*/

        Thread sonThreadAdapter=new Thread(){
            @Override
            public void run() {
               if (newarray!=null){queryView.setAdapter(adapter);}

            }};
        new Thread(sonThreadAdapter).start();


}



    private JSONArray queryRom() {

        ESClientQueryRoom esClientQueryRoom = new ESClientQueryRoomImpl();
        QueryMsg queryMsg = new QueryMsg("737373",1);
        Log.e(TAG,  "Romsg   is"+queryMsg);
        array = esClientQueryRoom.esclientQueryRoom(queryMsg);
        Log.e(TAG,  "Romsg   is"+array.toString());

return array;

    }



    private void initView() {
        queryView=(ListView) findViewById(R.id.queryView);

    }
}
