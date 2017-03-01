package com.esoon.vidyosample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.esoon.R;

public class SubActivityConsult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_consult);
		
	}
}
