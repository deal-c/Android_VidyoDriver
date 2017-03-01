package com.esoon.vidyosample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.esoon.R;

public class FlashActivity extends Activity {

	final int GOMain = 10002;

	Handler mHander = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			if (msg.what == GOMain) {
				Intent it = new Intent();
			//	it.setClass(FlashActivity.this, MainActivity.class);
				FlashActivity.this.startActivity(it);
				FlashActivity.this.finish();
			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flash);
	//	FlashActivity.this.requestWindowFeature(Window.FEATURE_NO_TITLE );
		
	
//		ImageView imgv = (ImageView) this.findViewById(R.id.imgflash);
//		// imgv.setOnClickListener(l);
//		mHander.sendEmptyMessageDelayed(GOMain, 2000);

	}
}
