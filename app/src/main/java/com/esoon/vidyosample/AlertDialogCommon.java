package com.esoon.vidyosample;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.esoon.R;

/**
 * Created by Administrator on 2017/3/16.
 */

public class AlertDialogCommon extends Dialog {

    private View rootView;
    private Context context;
    private int rid;

    public AlertDialogCommon(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public AlertDialogCommon(Context context, int rid) {
        super(context, R.style.dialog);
        this.context = context;
        this.rid = rid;
    }

    public void init(){
        rootView = LayoutInflater.from(context).inflate(rid,null);
        setContentView(rootView);
    }

    public void showDialog(){
        this.show();
        init();
    }

    public View getView(){
        return rootView;
    }
}